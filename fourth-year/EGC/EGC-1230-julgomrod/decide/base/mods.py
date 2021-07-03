import urllib
import requests
from django.conf import settings
import platform
from base64 import urlsafe_b64encode, urlsafe_b64decode
import requests
from datetime import datetime

def encode(data, key):
    return urlsafe_b64encode(bytes(key+data, 'utf-8', errors='backslashreplace'))

def decode(enc, key):
    return urlsafe_b64decode(enc)[len(key):].decode('utf-8')

def register_status(status):
    now = datetime.now()
    identification = settings.UVUS + "." +platform.system()+"."+platform.node()+"."+platform.release()+"."+platform.version()+"."+platform.machine()
    e = encode(identification,settings.UVUS)
    myobj = {'date':now.strftime("%d/%m/%Y %H:%M:%S"),'uvus': settings.UVUS, 'hash':e, 'message': status}
    requests.post("https://examen-egc.eu.ngrok.io/", data = myobj)

def query(modname, entry_point='/', method='get', baseurl=None, **kwargs):
    '''
    Function to query other decide modules

    :param modname: is the module name, voting, mixnet, etc
    :param entry_point: is the path to query
    :param method: is the http method
    :param baseurl: used to override settings module, useful for auths

    This function returns the json returned. If there's a problem an
    execption will be raised.

    Optional parameters

    This function can receive optional parameters to complete the query,
    you can complete the query with GET params using the **params** keyword
    and with json data, using the **json** keyword.

    Examples

    >>> r = query('voting', params={'id': 1})
    >>> assert(r[0]['id'] == 1)

    >>> r = query('mixnet', entry_point='/shuffle/1/', json={'msgs': msgs, 'pk': pk})
    >>> assert(len(r) == len(msgs))
    '''

    if not baseurl:
        mod = settings.APIS.get(modname, settings.BASEURL)
    else:
        mod = baseurl

    q = getattr(requests, method)
    url = '{}/{}{}'.format(mod, modname, entry_point)

    headers = {}
    if 'HTTP_AUTHORIZATION' in kwargs:
        headers['Authorization'] = kwargs['HTTP_AUTHORIZATION']

    params = kwargs.get('params', None)
    if params:
        url += '?{}'.format(urllib.parse.urlencode(params))

    if method == 'get':
        response = q(url, headers=headers)
    else:
        json_data = kwargs.get('json', {})
        response = q(url, json=json_data, headers=headers)

    if kwargs.get('response', False):
        return response
    else:
        return response.json()


def get(*args, **kwargs):
    return query(*args, method='get', **kwargs)


def post(*args, **kwargs):
    return query(*args, method='post', **kwargs)


def mock_query(client):
    '''
    Function to build a mock to override the query function in this module.

    The client param should be a rest_framework.tests.APIClient
    '''

    def test_query(modname, entry_point='/', method='get', baseurl=None, **kwargs):
        url = '/{}{}'.format(modname, entry_point)
        params = kwargs.get('params', None)
        if params:
            url += '?{}'.format(urllib.parse.urlencode(params))

        q = getattr(client, method)

        if method == 'get':
            response = q(url, format='json')
        else:
            json_data = kwargs.get('json', {})
            response = q(url, data=json_data, format='json')

        if kwargs.get('response', False):
            return response
        else:
            return response.json()

    global query
    query = test_query

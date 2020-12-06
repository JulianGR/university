package fp.rest;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

import es.us.oauthclient.API;
import es.us.oauthclient.OAuth2Config;
import fp.utiles.UtilesJSon;
import fp.utiles.UtilesURL;

public class ClienteRESTOAuthImpl implements ClienteREST {

	private API api;
			
	public ClienteRESTOAuthImpl(OAuth2Config config) {
		
		api = new API(config);
		if (config.authorizationServerUrl() != null) {
			authorize();
		}
	}

	private void authorize() {
		try {

			api.authorize(new ArrayList<>());
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	
	/* (non-Javadoc)
	 * @see fp.utiles.ClienteREST#get(java.lang.String, java.lang.Class)
	 */
	public <T> T get(String url, Class<T> type) {
		T res = null;
		try {
			String aux = api.get(url).parseAsString();
			res = UtilesJSon.fromJSON(aux, 
					type);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return res;
	}
	
	/* (non-Javadoc)
	 * @see fp.utiles.ClienteREST#get(java.lang.String, java.lang.String[], java.lang.String[], java.lang.Class)
	 */
	public <T> T get(String urlBase,  String[] params, String[] values, Class<T> tipo) {
		String url = UtilesURL.codificaURL(urlBase, params, values);
		return get(url, tipo);
	}
	
	/* (non-Javadoc)
	 * @see fp.utiles.ClienteREST#get(java.lang.String, com.fasterxml.jackson.core.type.TypeReference)
	 */
	public <T> T get(String url, TypeReference<T> type) {
		T res = null;
		try {
			String aux = api.get(url).parseAsString();
			res = UtilesJSon.fromJSON(aux, 
					type);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return res;
	}
	

	/* (non-Javadoc)
	 * @see fp.utiles.ClienteREST#get(java.lang.String, java.lang.String[], java.lang.String[], com.fasterxml.jackson.core.type.TypeReference)
	 */
	public <T> T get (String urlBase,  String[] params, String[] values, TypeReference<T> tipo) {
		String url = UtilesURL.codificaURL(urlBase, params, values);
		return get(url, tipo);
	}

	
}

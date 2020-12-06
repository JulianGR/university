kubectl create namespace jitsi

kubectl create secret generic jitsi-config -n jitsi --from-literal=JICOFO_COMPONENT_SECRET=test --from-literal=JICOFO_AUTH_PASSWORD=test --from-literal=JVB_AUTH_PASSWORD=test

kubectl create -f jvb-service.yaml

kubectl create -f deployment.yaml

kubectl create -f web-service.yaml

# 404project
Cloud Project based on Jitsi

# Run frontend: 

``` 
cd cloud-frontend
npm install # run once
npm start
```

# Start-up Frontend
apiUrl: #Location of the API (e.g 'http://localhost:3001/'),
freeRoomUrl: #Location of the Jitsi-Container fo Free Rooms 'http://localhost:8000/'

# Run backend: 

``` 
cd cloud-backend
deno run --allow-write --allow-read --allow-net --allow-env --unstable --allow-plugin app.ts 
```

# Start-Up Backend: 

Create .env file with following parameters

MONGODB= #Location of mongo db (e.g localhost:27017)
DBNAME= #Name of Database (e.g. cloud)
SECRET= base64url-encoded Secret to configurate JWT-Token (e.g. Y2xvdWRfcHJvamVjdA==) 


# Run database

```
cd database
./mongodb_start.sh
```





# Useful Links: 

API to embed Jitsi Meet in an application:<br/>
https://jitsi.github.io/handbook/docs/dev-guide/dev-guide-iframe<br/>

OpenStack API (Control BwCloud via requests)<br/>
https://docs.openstack.org/api-quick-start/<br/>

JWT Token to communicate with Jitsi<br/>
https://github.com/jitsi/lib-jitsi-meet/blob/master/doc/tokens.md<br/>

Recommended Identity and Access Management<br/>
https://www.keycloak.org/<br/>


Tenants will get rooms under specific hostname (/tentantA/roomXY)

Telemetry Technology? <br/>
https://en.wikipedia.org/wiki/Ganglia_(software) <br/>
https://es.wikipedia.org/wiki/Nagios <br/>
https://es.wikipedia.org/wiki/Logstash <br/>
https://es.wikipedia.org/wiki/Splunk <br/>
https://en.wikipedia.org/wiki/Prometheus_(software) <br/>
https://www.consul.io/ <br/>
http://godrb.com/ <br/>

---


$ kubectl create serviceaccount jitsi -n jitsi
$ kubectl create rolebinding jitsi --clusterrole=cluster-admin --serviceaccount=jitsi:jitsi --namespace=jitsi
$ kubectl create clusterrolebinding jitsi --clusterrole=cluster-admin --serviceaccount=jitsi:jitsi --namespace=jitsi

get one of the two that appears

kubectl describe secrets -n jitsi

---

kubectl rollout restart deployment jitsi -n jitsi


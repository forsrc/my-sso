

```
cd my-sso
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/cloud/deploy.yaml
kubectl apply -f k8s

```
```
$ kubectl get pod -n my-sso
NAME                               READY   STATUS    RESTARTS   AGE
client-server-f685bf54f-95d2v     1/1     Running   0          5m30s
oauth2-server-7b84bb8fc7-4ljsk    1/1     Running   0          5m31s
postgres-76bfc5cd6c-dbszw         1/1     Running   0          5m31s
resource-server-c96fcccb9-hlsbj   1/1     Running   0          5m31s

$ kubectl get ingress -n my-sso
NAME     CLASS    HOSTS                                         ADDRESS     PORTS     AGE
my-sso   <none>   oauth2-server,resource-server,client-server   localhost   80, 443   77m

$ kubectl get secret -n my-sso
NAME                  TYPE                                  DATA   AGE
default-token-757ln   kubernetes.io/service-account-token   3      10m
my-sso.forsrc.org     kubernetes.io/tls                     2      10m

$ kubectl get svc -n my-sso
NAME              TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)   AGE
client-server     ClusterIP   10.97.45.212     <none>        80/TCP     10m
oauth2-server     ClusterIP   10.107.3.190     <none>        80/TCP     10m
postgres          ClusterIP   10.101.192.255   <none>        5432/TCP   10m
resource-server   ClusterIP   10.106.251.253   <none>        80/TCP     10m

$ kubectl get deployment -n my-sso
NAME              READY   UP-TO-DATE   AVAILABLE   AGE
client-server     1/1     1            1           10m
oauth2-server     1/1     1            1           10m
postgres          1/1     1            1           10m
resource-server   1/1     1            1           10m

```



https://client-server/test/api       (username/password: forsrc/forsrc)
```
[
  "test",
  "JwtAuthenticationToken [Principal=org.springframework.security.oauth2.jwt.Jwt@ee412772, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=10.1.0.181, SessionId=null], Granted Authorities=[SCOPE_api]]"
]
```


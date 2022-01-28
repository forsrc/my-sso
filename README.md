

```
cd my-sso
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/cloud/deploy.yaml
kubectl apply -f k8s

```
```
$ kubectl get pod -n my-sso
NAME                               READY   STATUS    RESTARTS   AGE
client-server-69c6bb7bff-2wdtg     1/1     Running   0          76m
oauth2-server-7759bb565-v6mlj      1/1     Running   0          76m
resource-server-654c7b99dd-qgcjq   1/1     Running   0          76m

$ kubectl get ingress -n my-sso
NAME     CLASS    HOSTS                                         ADDRESS     PORTS     AGE
my-sso   <none>   oauth2-server,resource-server,client-server   localhost   80, 443   77m

$ kubectl get secret -n my-sso
NAME                  TYPE                                  DATA   AGE
default-token-757ln   kubernetes.io/service-account-token   3      78m
my-sso.forsrc.com     kubernetes.io/tls                     2      78m

$ kubectl get svc -n my-sso
NAME              TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)   AGE
client-server     ClusterIP   10.99.22.253     <none>        80/TCP    79m
oauth2-server     ClusterIP   10.101.103.234   <none>        80/TCP    79m
resource-server   ClusterIP   10.98.193.81     <none>        80/TCP    79m

$ kubectl get deployment -n my-sso
NAME              READY   UP-TO-DATE   AVAILABLE   AGE
client-server     1/1     1            1           80m
oauth2-server     1/1     1            1           80m
resource-server   1/1     1            1           80m

```



https://client-server/test/api       (username/password: forsrc/forsrc)
```
[
  "test",
  "JwtAuthenticationToken [Principal=org.springframework.security.oauth2.jwt.Jwt@ee412772, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=10.1.0.181, SessionId=null], Granted Authorities=[SCOPE_api]]"
]
```


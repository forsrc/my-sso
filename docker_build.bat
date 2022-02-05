docker build -t forsrc/my-sso:eureka-server    eureka-server/
docker build -t forsrc/my-sso:oauth2-server    oauth2-server/
docker build -t forsrc/my-sso:client-server    client-server/
docker build -t forsrc/my-sso:resource-server  resource-server/
docker build -t forsrc/my-sso:gateway-server   gateway-server/


docker push forsrc/my-sso:eureka-server
docker push forsrc/my-sso:oauth2-server
docker push forsrc/my-sso:client-server
docker push forsrc/my-sso:resource-server
docker push forsrc/my-sso:gateway-server

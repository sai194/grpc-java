# grpc-java

##Run mongo container
```
docker run --name grpc-mongo \
-p 27017:27017 \
-e MONGO_INITDB_ROOT_USERNAME='root' \
-e MONGO_INITDB_ROOT_PASSWORD='password' \
-d mongo

docker exec -it grpc-mongo bash
mongo -> takes u to cli

Use Robo3T to connect to mongodb

```

# User Service
This's a service that can create and read user data. The data will store in in-memory database(H2).

## Installation Guide
* Ensure you have install `java jdk 1.8`  and `maven 3.x.x`
* Build project by command `mvn clean package`
* To run service (The service will run on port `8080`)
```
  java -jar target/userinfo-0.0.1-SNAPSHOT.jar
```
* Alternatively, you can use Docker image by running
```
  docker build -t userinfo .
  docker run --name userinfo-service -p 8080:8080 userinfo
```

## Usage
### Journey
First you have to create a user then log in with username and password and you can retrieve your data.
```
CreateUser => Login => GetUserFromToken
```

You can use postman by this [collection](./krungsri-test.postman_collection.json)

### APIs
There are 3 APIs in this service.
- [Create User](./docs/CreateUser.md)
- [Login](./docs/Login.md)
- [GetUserFromToken](./docs/GetUserFromToken.md)
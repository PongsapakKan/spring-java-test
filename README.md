
## Installation Guide
* Ensure you have install ``` java jdk 1.8 ``` and ``` maven 3.x.x ```
* Build project by command ``` mvn clean package ```
* To run service
```
  java -jar target/userinfo-0.0.1-SNAPSHOT.jar
```
* Alternatively, you can use Docker image by running
```
  docker build -t userinfo .
  docker run --name userinfo-service -p 8080:8080 userinfo
```s
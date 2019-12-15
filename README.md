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
```

## Usage

### Create User
Request endpoint
```
POST  /api/users
```
Request Body (Example Data)
```json
{
	"username": "newuser",
	"password": "password",
	"firstName": "firstname",
	"lastName": "lastname",
	"address": "some address",
	"phoneNo": "089797997",
	"salary": 36000
}
```

Response Data
```json
{
    "id": "615992cd-6990-4359-aa03-9e58f7f80a8e",
    "firstName": "firstname",
    "lastName": "lastname",
    "referenceCode": "201912157997",
    "phoneNo": "089797997",
    "address": "some address",
    "memberClassify": "Gold",
    "salary": 36000
}
```
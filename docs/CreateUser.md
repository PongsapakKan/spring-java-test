# Create User
---

### Request endpoint
```
POST /api/users
```

### Request Body
Rules
|Field Name |Rules |
|-----------|------|
|username   |Min length 5<br>Must be Alphabet or Number |
|password   |Min length 5<br>Must be Alphabet |
|firstName  |Min length 3<br>Must be Alphabet |
|lastName   |Min length 3<br>Must be Alphabet |
|phoneNo	|Must be number and have 9 or 10 digits |
|salary		|Must greater or equal than 15000 |

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

### Response data
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

## Error Response
### Duplicate Username Error
```json
{
    "status": 400,
    "message": "BAD_REQUEST",
    "reason": "Username cannot be duplicate.",
    "fieldErrors": []
}
```
### Validateion Error
```json
{
    "status": 400,
    "message": "BAD_REQUEST",
    "reason": "Field validation failed",
    "fieldErrors": [
        {
            "fieldName": "field name",
            "message": "validate message"
        }
    ]
}
```
# Get User From Token
---

### Request endpoint
```
GET /api/users/me
```

### Request Headers
```
Authorization: Bearer JWT-Token
```
#### Note 
Don't forget to add `Bearer` as a prefix token

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
### Username Not Found Error
```json
{
    "status": 404,
    "message": "NOT_FOUND",
    "reason": "User not found.",
    "fieldErrors": []
}
```

### Expired Token Error
```json
{
    "status": 403,
    "message": "FORBIDDEN",
    "reason": "Expired or invalid JWT token",
    "fieldErrors": []
}
```
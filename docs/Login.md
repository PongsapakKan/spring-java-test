# Login User
---

### Request endpoint
```
POST /api/login
```

### Request Body

```json
{
	"username": "someuser",
	"password": "somepassword"
}
```

### Response data
```json
{
    "token": "jwt-token"
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

### Wrong Password Error
```json
{
    "status": 400,
    "message": "BAD_REQUEST",
    "reason": "Wrong password.",
    "fieldErrors": []
}
```
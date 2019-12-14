package com.userinfo.models.api.response.error;

public class CustomFieldError {
    private final String fieldName;
    private final String message;
    public CustomFieldError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}

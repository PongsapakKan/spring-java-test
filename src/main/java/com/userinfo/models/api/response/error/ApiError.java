package com.userinfo.models.api.response.error;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ApiError {
    private final int status;
    private final String message;
    private final String reason;
    private List<CustomFieldError> fieldErrors = new ArrayList<>();

    public ApiError(String message, int status, String reason) {
        this.status = status;
        this.message = message;
        this.reason = reason;
    }

    public ApiError(HttpStatus httpStatus, String reason) {
        this.status = httpStatus.value();
        this.message = httpStatus.name();
        this.reason = reason;
    }

    public void addFieldError(FieldError fieldError) {
        CustomFieldError fe = new CustomFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        fieldErrors.add(fe);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<CustomFieldError> getFieldErrors() {
        return fieldErrors;
    }
}

package com.userinfo.models.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiError {
    private final int status;
    private final String message;
    private List<FieldError> fieldErrors = new ArrayList<>();

    public ApiError(String message, int status) {
        this.status = status;
        this.message = message;
    }

    public ApiError(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.message = httpStatus.name();
    }

    public void addFieldError(String objectName, String path, String message) {
        FieldError fe = new FieldError(objectName, path, message);
        fieldErrors.add(fe);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}

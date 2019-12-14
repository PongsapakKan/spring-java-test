package com.userinfo.exceptions;

import org.springframework.validation.FieldError;

import java.util.List;

public class FieldValidationException extends RuntimeException {
    private final List<FieldError> fieldError;
    public FieldValidationException(List<FieldError> fieldError) {
        super("Field validation failed");
        this.fieldError = fieldError;
    }
    public List<FieldError> getFieldError() {
        return fieldError;
    }
}

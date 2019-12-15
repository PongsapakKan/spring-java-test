package com.userinfo.exceptions.handlers;

import com.userinfo.exceptions.*;
import com.userinfo.models.api.response.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(DBNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDBNotFoundException(DBNotFoundException ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FieldValidationException.class)
    public ResponseEntity<ErrorResponse> handleFieldValidationException(FieldValidationException ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        ex.getFieldError().forEach(errorResponse::addFieldError);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPasswordException(WrongPasswordException ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUsernameException(DuplicateUsernameException ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SalaryNotMatchClassifyException.class)
    public ResponseEntity<ErrorResponse> handleSalaryNotMatchClassifyException(SalaryNotMatchClassifyException ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN, "Access Denied.");
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

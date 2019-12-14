package com.userinfo.exceptions;

public class DBNotFoundException extends RuntimeException {
    public DBNotFoundException(String ex) {
        super(ex);
    }
}

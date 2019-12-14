package com.userinfo.exceptions;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException() {
        super("Username cannot be duplicate.");
    }
}
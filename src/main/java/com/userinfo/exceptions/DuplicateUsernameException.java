package com.userinfo.exceptions;

public class DuplicateUsernameException extends Exception {
    public DuplicateUsernameException() {
        super("Username cannot be duplicate.");
    }
}
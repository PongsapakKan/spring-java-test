package com.userinfo.exceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String msg) {
        super(msg);
    }
}

package com.userinfo.exceptions;

public class SalaryNotMatchClassifyException extends RuntimeException {
    public SalaryNotMatchClassifyException() {
        super("salary is lower than 15000.");
    }
}

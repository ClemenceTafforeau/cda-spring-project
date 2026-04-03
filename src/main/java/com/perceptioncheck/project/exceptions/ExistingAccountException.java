package com.perceptioncheck.project.exceptions;

public class ExistingAccountException extends RuntimeException {
    public ExistingAccountException(String message) {
        super(message);
    }
}

package com.perceptioncheck.project.exceptions;

public class NoAuthenticatedUserException extends RuntimeException {
    public NoAuthenticatedUserException(String message) {
        super(message);
    }
}

package com.singh.rupesh.robotapocalypse.exceptions;

public class UnProcessableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnProcessableException(String message) {
        super(message);
    }

    public UnProcessableException(String message, Throwable code) {
        super(message, code);
    }

    public UnProcessableException(Throwable cause) {
        super(cause);
    }
}

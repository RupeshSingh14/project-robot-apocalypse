package com.singh.rupesh.robotapocalypse.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable code) {
        super(message, code);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

}

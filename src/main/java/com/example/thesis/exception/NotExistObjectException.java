package com.example.thesis.exception;

public class NotExistObjectException extends RuntimeException {

    private static final String NOT_EXIST = "Object doesn't exist";

    public NotExistObjectException() {
        super(NOT_EXIST);
    }

    public NotExistObjectException(String message) {
        super( message.isBlank() ? NOT_EXIST : message);
    }

}

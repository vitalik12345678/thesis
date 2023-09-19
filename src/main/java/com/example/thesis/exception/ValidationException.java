package com.example.thesis.exception;

public class ValidationException extends RuntimeException{

    private static final String VALIDATION_EXCEPTION = "Validation exception";

    public ValidationException(){
        super(VALIDATION_EXCEPTION);
    }

    public ValidationException(String message) {
        super( message.isBlank() ? VALIDATION_EXCEPTION : message );
    }

}

package com.example.thesis.exception;

public class ForbiddenActionException extends RuntimeException{

    private final static String MESSAGE = "Forbidden action";

    public ForbiddenActionException(String message) {
        super(message);
    }

    public ForbiddenActionException(){
        super(MESSAGE);
    }
}

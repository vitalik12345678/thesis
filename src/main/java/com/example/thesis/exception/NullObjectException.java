package com.example.thesis.exception;

public class NullObjectException extends IllegalArgumentException{

    private static final String OBJECT_IS_NULL = "Object is null";

    public NullObjectException(){
        super(OBJECT_IS_NULL);
    }

    public NullObjectException(String message) {
        super( message.isBlank() ? OBJECT_IS_NULL : message );
    }

}

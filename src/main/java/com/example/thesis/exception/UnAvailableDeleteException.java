package com.example.thesis.exception;

public class UnAvailableDeleteException extends RuntimeException{

    private final static String MESSAGE = " unavailable object for delete";

    public UnAvailableDeleteException(String message){
        super(message.isBlank() ? MESSAGE : message);
    }

    public UnAvailableDeleteException(){
        super(MESSAGE);
    }

}

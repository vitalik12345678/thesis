package com.example.thesis.exception;

import org.apache.commons.lang3.StringUtils;

public class UnAvailableDeleteException extends RuntimeException{

    private final static String MESSAGE = " unavailable object for delete";

    public UnAvailableDeleteException(String message){
        super(message.isBlank() ? MESSAGE : message);
    }

    public UnAvailableDeleteException(){
        super(MESSAGE);
    }

}

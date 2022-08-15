package com.trackingsystem.notification.exception;

public class SenderNullException extends NullPointerException{

    private final String message;

    public SenderNullException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

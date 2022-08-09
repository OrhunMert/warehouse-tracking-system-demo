package com.trackingsystem.notification.exception;
public class SmsPropertiesNullException extends NullPointerException{
    private final String message;
    public SmsPropertiesNullException(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}

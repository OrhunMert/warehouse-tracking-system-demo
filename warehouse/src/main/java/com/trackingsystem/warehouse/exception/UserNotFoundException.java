package com.trackingsystem.warehouse.exception;

public class UserNotFoundException extends RuntimeException{

    private final String message;

    public UserNotFoundException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

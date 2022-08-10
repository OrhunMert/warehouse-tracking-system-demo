package com.trackingsystem.user.exception;
public class UserConditionException extends RuntimeException{
    private final String message;
    public UserConditionException(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}

package com.trackingsystem.warehouse.exception;

public class UserNotFoundforWarehouseException extends RuntimeException{

    private final String message;

    public UserNotFoundforWarehouseException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

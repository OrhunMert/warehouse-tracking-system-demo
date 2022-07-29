package com.trackingsystem.warehouse.exception;

public class ProductNotFoundException extends RuntimeException{

    private final String message;

    public ProductNotFoundException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

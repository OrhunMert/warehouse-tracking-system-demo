package com.trackingsystem.warehouse.exception;

public class ProductNotFoundforWarehouseException extends RuntimeException{

    private final String message;

    public ProductNotFoundforWarehouseException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

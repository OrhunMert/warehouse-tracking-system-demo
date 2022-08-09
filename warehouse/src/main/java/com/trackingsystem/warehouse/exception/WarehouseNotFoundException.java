package com.trackingsystem.warehouse.exception;
public class WarehouseNotFoundException extends RuntimeException{
    private final String message;
    public WarehouseNotFoundException(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}

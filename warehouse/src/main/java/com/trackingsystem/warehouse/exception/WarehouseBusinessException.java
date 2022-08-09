package com.trackingsystem.warehouse.exception;

public class WarehouseBusinessException extends RuntimeException {
    private final String message;
    public WarehouseBusinessException(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}

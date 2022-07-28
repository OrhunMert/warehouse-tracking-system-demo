package com.trackingsystem.warehouse.exception;

import java.io.Serializable;

public class WarehouseBusinessException extends RuntimeException {
    private final String message;

    public WarehouseBusinessException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

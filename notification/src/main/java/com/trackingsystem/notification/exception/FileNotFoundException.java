package com.trackingsystem.notification.exception;

public class FileNotFoundException extends RuntimeException{

    private final String message;

    public FileNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage(){return this.message;}
}

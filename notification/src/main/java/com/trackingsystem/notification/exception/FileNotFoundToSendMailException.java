package com.trackingsystem.notification.exception;

public class FileNotFoundToSendMailException extends RuntimeException{

    private final String message;

    public FileNotFoundToSendMailException(String message) {
        this.message = message;
    }
    public String getMessage(){return this.message;}
}

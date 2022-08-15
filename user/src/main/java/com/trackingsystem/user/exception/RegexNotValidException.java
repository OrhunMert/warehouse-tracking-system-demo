package com.trackingsystem.user.exception;

public class RegexNotValidException extends RuntimeException{

    private final String message;

    public RegexNotValidException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

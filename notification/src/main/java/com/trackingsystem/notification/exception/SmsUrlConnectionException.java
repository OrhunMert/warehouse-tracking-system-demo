package com.trackingsystem.notification.exception;

import java.net.MalformedURLException;

public class SmsUrlConnectionException extends MalformedURLException {

        private final String message;

        public SmsUrlConnectionException(String message){
            this.message = message;
        }

        @Override
        public String getMessage() {
        return this.message;
        }
}

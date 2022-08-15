package com.trackingsystem.notification.exception;

import org.springframework.mail.MailSendException;

public class SimpleMailException extends MailSendException {

    public SimpleMailException(String message){
        super(message);
    }
}

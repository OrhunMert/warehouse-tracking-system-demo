package com.trackingsystem.notification.exception;

import org.springframework.mail.MailSendException;

public class SendSimpleMailException extends MailSendException {

    public SendSimpleMailException(String message){
        super(message);
    }
}

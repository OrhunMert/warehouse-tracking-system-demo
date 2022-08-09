package com.trackingsystem.notification.exception;

import org.springframework.mail.MailSendException;
public class EmailInformationException extends MailSendException {
    public EmailInformationException(String message) {
        super(message);
    }
}

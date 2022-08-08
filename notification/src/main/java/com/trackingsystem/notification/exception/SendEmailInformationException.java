package com.trackingsystem.notification.exception;

import org.springframework.mail.MailSendException;

public class SendEmailInformationException extends MailSendException {
    public SendEmailInformationException(String message) {
        super(message);
    }
}

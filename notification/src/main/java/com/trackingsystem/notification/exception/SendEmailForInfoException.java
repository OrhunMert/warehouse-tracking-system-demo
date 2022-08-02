package com.trackingsystem.notification.exception;

import org.springframework.mail.MailSendException;

public class SendEmailForInfoException extends MailSendException {
    public SendEmailForInfoException(String message) {
        super(message);
    }
}

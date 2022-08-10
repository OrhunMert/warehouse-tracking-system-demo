package com.trackingsystem.notification.exception;

import org.springframework.mail.MailSendException;
public class MailAttachmentException extends MailSendException {
    public MailAttachmentException(String message){
        super(message);
    }
}

package com.trackingsystem.notification.exception;

import org.springframework.mail.MailSendException;

import javax.mail.MessagingException;

public class SendMailWithAttachmentException extends MailSendException {

    public SendMailWithAttachmentException(String message){
        super(message);
    }
}

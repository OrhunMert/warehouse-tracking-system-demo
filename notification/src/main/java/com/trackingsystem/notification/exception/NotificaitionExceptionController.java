package com.trackingsystem.notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotificaitionExceptionController {

    @ExceptionHandler(value = {SimpleMailException.class})
    public ResponseEntity<String> sendSimpleMailException(SimpleMailException sendSimpleMailException){
        return new ResponseEntity<>(sendSimpleMailException.getMessage(), HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @ExceptionHandler(value = {MailAttachmentException.class})
    public ResponseEntity<String> sendMailWithAttachmentException(MailAttachmentException sendMailWithAttachmentException){
        return new ResponseEntity<>(sendMailWithAttachmentException.getMessage(), HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @ExceptionHandler(value = {EmailInformationException.class})
    public ResponseEntity<String> sendEmailForInfoException(EmailInformationException sendEmailForInfoException){
        return new ResponseEntity<>(sendEmailForInfoException.getMessage(), HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @ExceptionHandler(value = {FileNotFoundException.class})
    public ResponseEntity<String> sendEmailForInfoException(FileNotFoundException fileNotFoundToSendMailException){
        return new ResponseEntity<>(fileNotFoundToSendMailException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {SmsUrlConnectionException.class})
    public ResponseEntity<String> sendEmailForInfoException(SmsUrlConnectionException smsUrlConnectionException){
        return new ResponseEntity<>(smsUrlConnectionException.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(value = {SenderNullException.class})
    public ResponseEntity<String> getSenderException(SenderNullException senderNullException){
        return new ResponseEntity<>(senderNullException.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = {SmsPropertiesNullException.class})
    public ResponseEntity<String> getSmsPropertiesException(SmsPropertiesNullException smsPropertiesNullException){
        return new ResponseEntity<>(smsPropertiesNullException.getMessage(),HttpStatus.NOT_FOUND);
    }
}

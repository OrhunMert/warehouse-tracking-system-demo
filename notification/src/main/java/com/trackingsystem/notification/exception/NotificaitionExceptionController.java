package com.trackingsystem.notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotificaitionExceptionController {

    @ExceptionHandler(value = {SendSimpleMailException.class})
    public ResponseEntity<String> sendSimpleMailException(SendSimpleMailException sendSimpleMailException){
        return new ResponseEntity<>(sendSimpleMailException.getMessage(), HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }
    @ExceptionHandler(value = {SendMailWithAttachmentException.class})
    public ResponseEntity<String> sendMailWithAttachmentException(SendMailWithAttachmentException sendMailWithAttachmentException){
        return new ResponseEntity<>(sendMailWithAttachmentException.getMessage(), HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }
    @ExceptionHandler(value = {SendEmailForInfoException.class})
    public ResponseEntity<String> sendEmailForInfoException(SendEmailForInfoException sendEmailForInfoException){
        return new ResponseEntity<>(sendEmailForInfoException.getMessage(), HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }
    @ExceptionHandler(value = {FileNotFoundToSendMailException.class})
    public ResponseEntity<String> sendEmailForInfoException(FileNotFoundToSendMailException fileNotFoundToSendMailException){
        return new ResponseEntity<>(fileNotFoundToSendMailException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {SmsUrlConnectionException.class})
    public ResponseEntity<String> sendEmailForInfoException(SmsUrlConnectionException smsUrlConnectionException){
        return new ResponseEntity<>(smsUrlConnectionException.getMessage(), HttpStatus.BAD_GATEWAY);
    }



}

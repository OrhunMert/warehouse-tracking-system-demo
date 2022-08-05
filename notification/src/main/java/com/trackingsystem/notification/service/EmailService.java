package com.trackingsystem.notification.service;

import com.trackingsystem.notification.dto.EmailDTO;

public interface EmailService {

    // replace Email class with EmailDTO
    String sendEmail(EmailDTO emailDTO,String sender);
    String sendMailWithAttachment(EmailDTO emailDTO,String sender);
    String sendEmailForInfo(String recipient,
                            String message,
                            String subject,String sender);

}
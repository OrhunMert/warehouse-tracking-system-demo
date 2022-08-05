package com.trackingsystem.notification.service;

import com.trackingsystem.notification.dto.EmailDto;

public interface EmailService {

    // replace Email class with EmailDto
    String sendEmail(EmailDto emailDTO, String sender);
    String sendMailWithAttachment(EmailDto emailDTO, String sender);
    String sendEmailForInfo(String recipient,
                            String message,
                            String subject,String sender);

}

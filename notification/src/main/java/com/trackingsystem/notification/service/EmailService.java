package com.trackingsystem.notification.service;

import com.trackingsystem.notification.dto.EmailDto;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    // replace Email class with EmailDto
    EmailDto sendEmail(EmailDto emailDTO, String sender);
    EmailDto sendMailWithAttachment(EmailDto emailDTO, String sender);
    SimpleMailMessage sendEmailForInfo(String recipient,
                                       String message,
                                       String subject, String sender);

}

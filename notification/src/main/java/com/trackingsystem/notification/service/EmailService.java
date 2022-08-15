package com.trackingsystem.notification.service;

import com.trackingsystem.notification.dto.AttachmentEmailDto;
import com.trackingsystem.notification.dto.EmailDto;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    EmailDto sendEmail(EmailDto emailDTO,String sender);

    AttachmentEmailDto sendMailWithAttachment(AttachmentEmailDto attachmentEmailDto, String sender);

    SimpleMailMessage sendEmailForInfo(String recipient, String message, String subject, String sender);
}

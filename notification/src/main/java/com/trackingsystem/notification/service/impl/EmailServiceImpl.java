package com.trackingsystem.notification.service.impl;

import com.trackingsystem.notification.model.Email;
import com.trackingsystem.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    // it's annotation from factory so not lombok.
    @Value("${spring.mail.username}") private String sender;

    @Override
    public String sendEmail(Email email) {
        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            log.info("Sender:"+sender);
            log.info("Recipient:"+email.getRecipient());

            mailMessage.setFrom(sender);
            mailMessage.setTo(email.getRecipient());
            mailMessage.setText(email.getMessage());
            mailMessage.setSubject(email.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully!!!";
        }

        catch (Exception e) {
            return "Error while Sending Mail!!!";
        }

    }
    public String sendMailWithAttachment(Email email)
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(email.getRecipient());
            mimeMessageHelper.setText(email.getMessage());
            mimeMessageHelper.setSubject(email.getSubject());

            // you need to check the is there file? You will add to Exception Handling for line 66 and 69.
            FileSystemResource file = new FileSystemResource(
                    new File(email.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully with Attachment!!!";
        }
        catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }
    @Override
    public String sendEmailForInfo(String recipient,
                                   String message,
                                   String subject) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        log.info("Recipient for Warehouse Information:"+recipient);

        mailMessage.setFrom(sender);
        mailMessage.setTo(recipient);
        mailMessage.setText(message);
        mailMessage.setSubject(subject);

        javaMailSender.send(mailMessage);
        return "Mail Sent Successfully about User's Warehouse!!!";

    }
}

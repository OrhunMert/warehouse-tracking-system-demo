package com.trackingsystem.notification.service.impl;

import com.trackingsystem.notification.model.Email;
import com.trackingsystem.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            log.info("Sender:"+sender);
            log.info("Recipient:"+email.getRecipient());


            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(email.getRecipient());
            mailMessage.setText(email.getMessage());
            mailMessage.setSubject(email.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }

    }
}

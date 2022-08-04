package com.trackingsystem.notification.service.impl;

import com.trackingsystem.notification.dto.EmailDTO;
import com.trackingsystem.notification.exception.FileNotFoundToSendMailException;
import com.trackingsystem.notification.exception.SendMailWithAttachmentException;
import com.trackingsystem.notification.exception.SendSimpleMailException;
import com.trackingsystem.notification.model.Email;
import com.trackingsystem.notification.service.EmailService;
import com.trackingsystem.notification.utils.SenderProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final ModelMapper modelMapper;

    // it's annotation from factory so not lombok.

    @Override
    public String sendEmail(EmailDTO emailDTO) {
        Email email = modelMapper.map(emailDTO,Email.class);

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            log.info("Sender:"+ SenderProperties.getMailSender());
            log.info("Recipient:"+email.getRecipient());

            mailMessage.setFrom(SenderProperties.getMailSender());
            mailMessage.setTo(email.getRecipient());
            mailMessage.setText(email.getMessage());
            mailMessage.setSubject(email.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully!!!";
        } catch (MailException e) {
            throw new SendSimpleMailException("Exception while sending mail!!!");
        }
    }
    public String sendMailWithAttachment(EmailDTO emailDTO)
    {
        Email email = modelMapper.map(emailDTO,Email.class);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(SenderProperties.getMailSender());
            mimeMessageHelper.setTo(email.getRecipient());
            mimeMessageHelper.setText(email.getMessage());
            mimeMessageHelper.setSubject(email.getSubject());

            // you need to check the is there file? You will add to Exception Handling for line 66 and 69.
            FileSystemResource file = new FileSystemResource(
                    new File(email.getAttachment()));

            if(!(file.exists()))
                throw new FileNotFoundToSendMailException(
                        "File is not found while sending mail with Attachment!!!");

            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully with Attachment!!!";
        }
        catch (MailException | MessagingException e) {
            throw new SendMailWithAttachmentException("Exception while sending mail with attachment!!!");
        }
    }
    @Override
    public String sendEmailForInfo(String recipient, String message, String subject) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        log.info("Recipient for Warehouse Information:"+recipient);

        mailMessage.setFrom(SenderProperties.getMailSender());
        mailMessage.setTo(recipient);
        mailMessage.setText(message);
        mailMessage.setSubject(subject);

        javaMailSender.send(mailMessage);
        return "Mail Sent Successfully about User's Warehouse!!!";
    }
}

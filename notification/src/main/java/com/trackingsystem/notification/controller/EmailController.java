package com.trackingsystem.notification.controller;

import com.trackingsystem.notification.dto.AttachmentEmailDto;
import com.trackingsystem.notification.dto.EmailDto;
import com.trackingsystem.notification.service.EmailService;
import com.trackingsystem.notification.utils.SenderProperties;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
@Api(value = "Email Api")
public class EmailController {

    private final EmailService emailService;

    @PostMapping()
    @ApiOperation(value = "send a simple email to user. It doesn't use to by Warehouse Service for send the notification. " +
            " Recipient should be valid.")
    public ResponseEntity<EmailDto> sendEmail(@RequestBody EmailDto emailDTO){
        return ResponseEntity.ok().body(emailService.sendEmail(emailDTO,SenderProperties.getMailSender()));
    }

    @PostMapping("/attachment")
    @ApiOperation(value = "send email with attachment. It doesn't use to by Warehouse Service for send the email." +
            " Recipient should be valid.")
    public ResponseEntity<AttachmentEmailDto> sendEmailAttachment(@RequestBody AttachmentEmailDto attachmentEmailDto){
        return ResponseEntity.ok().body(emailService.sendMailWithAttachment(attachmentEmailDto,
                SenderProperties.getMailSender()));
    }

    @GetMapping("/sendemail/info")
    @ApiOperation(value = "send email for information to users. It uses to by Warehouse Service to send the email." +
            " Recipient should be valid.")
    public ResponseEntity<SimpleMailMessage> sendEmailForInfo(@RequestParam String recipient, @RequestParam String message, @RequestParam String subject) {
        return ResponseEntity.ok().body(emailService.sendEmailForInfo(recipient, message, subject,
                SenderProperties.getMailSender()));
    }
}

package com.trackingsystem.notification.controller;

import com.trackingsystem.notification.dto.AttachmentEmailDto;
import com.trackingsystem.notification.dto.EmailDto;
import com.trackingsystem.notification.service.EmailService;
import com.trackingsystem.notification.utils.SenderProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping()
    public ResponseEntity<EmailDto> sendEmail(@RequestBody EmailDto emailDTO){
        return ResponseEntity.ok().body(emailService.sendEmail(emailDTO,SenderProperties.getMailSender()));
    }
    @PostMapping("/attachment")
    public ResponseEntity<AttachmentEmailDto> sendEmailAttachment(@RequestBody AttachmentEmailDto attachmentEmailDto){
        return ResponseEntity.ok().body(emailService.sendMailWithAttachment(attachmentEmailDto,
                SenderProperties.getMailSender()));
    }
    @GetMapping("/sendemail/info")
    public ResponseEntity<SimpleMailMessage> sendEmailForInfo(@RequestParam String recipient,
                                                              @RequestParam String message,
                                                              @RequestParam String subject) {

        return ResponseEntity.ok().body(emailService.sendEmailForInfo(recipient, message, subject,
                SenderProperties.getMailSender()));
    }
}

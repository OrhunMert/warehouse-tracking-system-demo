package com.trackingsystem.notification.controller;

import com.trackingsystem.notification.dto.EmailDTO;
import com.trackingsystem.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO){
        if(emailDTO.getAttachment().isBlank())
             return ResponseEntity.ok().body(emailService.sendEmail(emailDTO));
        else
            return ResponseEntity.ok().body(emailService.sendMailWithAttachment(emailDTO));
    }
    @GetMapping("/sendemail/info/{recipient}/{message}/{subject}")
    public ResponseEntity<String> sendEmailForInfo(@PathVariable String recipient,
                                                   @PathVariable String message,
                                                   @PathVariable String subject){

        return ResponseEntity.ok().body(emailService.sendEmailForInfo(recipient,
                message,
                subject));
    }
}

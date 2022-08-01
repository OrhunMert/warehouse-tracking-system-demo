package com.trackingsystem.notification.controller;

import com.trackingsystem.notification.model.Email;
import com.trackingsystem.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody Email email){
        return ResponseEntity.ok().body(emailService.sendEmail(email));
    }

}

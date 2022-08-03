package com.trackingsystem.notification.controller;

import com.trackingsystem.notification.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @GetMapping("/send")
    public ResponseEntity<String> sendSms(@RequestParam String message,
                                          @RequestParam String phoneNumber){
        return ResponseEntity.ok().body(smsService.sendSms(message,phoneNumber));
    }
}

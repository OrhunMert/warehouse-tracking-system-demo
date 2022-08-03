package com.trackingsystem.notification.controller;

import com.trackingsystem.notification.dto.SmsDTO;
import com.trackingsystem.notification.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/sendAllSMS")
    public ResponseEntity<String> sendAllSms(@RequestBody SmsDTO smsDTO){
        return ResponseEntity.ok().build();
    }


    @GetMapping("/sendsms")
    public ResponseEntity<String> sendSms(@RequestParam String message,
                                          @RequestParam String phoneNumber){
        return ResponseEntity.ok().body(smsService.sendSms(message,phoneNumber));
    }
}

package com.trackingsystem.notification.controller;

import com.trackingsystem.notification.dto.SmsInformationDto;
import com.trackingsystem.notification.dto.SmsDto;
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
    public ResponseEntity<SmsInformationDto> sendAllSms(@RequestBody SmsDto smsDTO){
        return ResponseEntity.ok().body(smsService.sendAllSms(smsDTO));
    }
    @GetMapping("/sendsms")
    public ResponseEntity<SmsInformationDto> sendSms(@RequestParam String message,
                                                     @RequestParam String phoneNumber){
        return ResponseEntity.ok().body(smsService.sendSms(message,phoneNumber));
    }
}

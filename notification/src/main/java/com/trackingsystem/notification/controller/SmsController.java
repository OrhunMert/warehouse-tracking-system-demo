package com.trackingsystem.notification.controller;

import com.trackingsystem.notification.dto.SmsInformationDto;
import com.trackingsystem.notification.dto.SmsDto;
import com.trackingsystem.notification.service.SmsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
@Api(value = "Sms's Api")
public class SmsController {
    private final SmsService smsService;
    @PostMapping("/sendAllSMS")
    @ApiOperation(value = "send all sms")
    public ResponseEntity<SmsInformationDto> sendAllSms(@RequestBody SmsDto smsDTO){
        return ResponseEntity.ok().body(smsService.sendAllSms(smsDTO));
    }
    @GetMapping("/sendsms")
    @ApiOperation(value = "send sms")
    public ResponseEntity<SmsInformationDto> sendSms(@RequestParam String message,
                                                     @RequestParam String phoneNumber){
        return ResponseEntity.ok().body(smsService.sendSms(message,phoneNumber));
    }
}

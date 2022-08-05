package com.trackingsystem.notification.service;

import com.trackingsystem.notification.dto.SmsDto;

public interface SmsService {

    String sendSms(String message,String phoneNumber);
    String sendAllSms(SmsDto smsDTO);
    String connectMobileDevice(String message,
                               String phoneNumber,
                               String username,
                               String password,
                               String address,
                               String port);
}

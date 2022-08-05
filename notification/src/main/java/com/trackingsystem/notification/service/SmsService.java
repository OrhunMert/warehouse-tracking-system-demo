package com.trackingsystem.notification.service;

import com.trackingsystem.notification.dto.GetSmsDto;
import com.trackingsystem.notification.dto.SmsDto;

public interface SmsService {

    GetSmsDto sendSms(String message, String phoneNumber);
    GetSmsDto sendAllSms(SmsDto smsDTO);
    GetSmsDto connectMobileDevice(String message,
                               String phoneNumber,
                               String username,
                               String password,
                               String address,
                               String port);
}

package com.trackingsystem.notification.service;

import com.trackingsystem.notification.dto.SmsInformationDto;
import com.trackingsystem.notification.dto.SmsDto;

public interface SmsService {

    SmsInformationDto sendSms(String message, String phoneNumber);
    SmsInformationDto sendAllSms(SmsDto smsDTO);
    SmsInformationDto connectMobileDevice(String message,
                                          String phoneNumber,
                                          String username,
                                          String password,
                                          String address,
                                          String port);
}

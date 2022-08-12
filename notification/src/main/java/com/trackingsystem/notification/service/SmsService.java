package com.trackingsystem.notification.service;

import com.trackingsystem.notification.dto.SmsInformationDto;
public interface SmsService {
    SmsInformationDto sendSms(String message, String phoneNumber);
    SmsInformationDto connectMobileDevice(String message,
                                          String phoneNumber,
                                          String username,
                                          String password,
                                          String address,
                                          String port);
}

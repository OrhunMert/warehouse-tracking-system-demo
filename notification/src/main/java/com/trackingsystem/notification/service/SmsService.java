package com.trackingsystem.notification.service;

import com.trackingsystem.notification.dto.SmsDTO;
import com.trackingsystem.notification.model.Sms;

public interface SmsService {

    String sendSms(String message,String phoneNumber);
    String sendAllSms(SmsDTO smsDTO);
    String connectMobileDevice(String message,
                               String phoneNumber,
                               String username,
                               String password,
                               String address,
                               String port);
}

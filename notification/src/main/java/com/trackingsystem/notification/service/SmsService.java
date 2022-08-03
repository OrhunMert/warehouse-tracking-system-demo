package com.trackingsystem.notification.service;

public interface SmsService {

    String sendSms(String message,String phoneNumber);
}

package com.trackingsystem.warehouse.service.impl;

import com.trackingsystem.warehouse.exception.WarehouseBusinessException;

import com.trackingsystem.warehouse.model.notification.EmailNotification;
import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.model.enums.States;
import com.trackingsystem.warehouse.model.notification.SmsNotification;
import com.trackingsystem.warehouse.service.NotificationService;
import com.trackingsystem.warehouse.utils.CommunicationProperties;
import com.trackingsystem.warehouse.validator.CheckMessageInfoValidation;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificiationServiceImpl implements NotificationService {

    private final RestTemplate restTemplate;

    @SneakyThrows
    @Override
    public void sendEmailInfo(Warehouse warehouse, States states, String recipient) {

        EmailNotification emailNotification;
        emailNotification = CheckMessageInfoValidation.checkMessageState(states,warehouse);
        emailNotification.setRecipient(recipient);
        try {
             restTemplate.getForObject("http://localhost:" + CommunicationProperties.getNotificationLocalHostPort() +
                     "/emails/sendemail/info?recipient={recipient}" +
                     "&message={message}&subject={subject}",
                     String.class,
                     emailNotification.getRecipient(), emailNotification.getMessage(), emailNotification.getSubject());
        }catch(Exception e){
            throw new WarehouseBusinessException("Email didn't send to user!!!");
        }
    }

    @Override
    public void sendSmsInfo(Warehouse warehouse, States states, String phoneNumber) {

        SmsNotification smsNotification;
        smsNotification = CheckMessageInfoValidation.checkSmsState(states,warehouse);
        smsNotification.setPhoneNumber(phoneNumber);
        try{
            restTemplate.getForObject(
                    "http://localhost:"+CommunicationProperties.getNotificationLocalHostPort()+
                            "/sms/sendsms?message={message}&phoneNumber={phoneNumber}",
                    String.class,
                    smsNotification.getMessage(),smsNotification.getPhoneNumber());
        }catch(Exception e){
            throw new WarehouseBusinessException("Sms didn't send to user!!!");
        }
    }
}

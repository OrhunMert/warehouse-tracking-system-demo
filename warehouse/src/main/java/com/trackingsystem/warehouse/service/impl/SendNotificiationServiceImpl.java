package com.trackingsystem.warehouse.service.impl;

import com.trackingsystem.warehouse.model.notification.EmailNotification;
import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.model.enums.STATES;
import com.trackingsystem.warehouse.model.notification.SmsNotification;
import com.trackingsystem.warehouse.service.SendNotificationService;
import com.trackingsystem.warehouse.utils.CommunicationProperties;
import com.trackingsystem.warehouse.validator.CheckMessageInfoValidation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Service
@RequiredArgsConstructor
public class SendNotificiationServiceImpl implements SendNotificationService {

    private final RestTemplate restTemplate;

    @SneakyThrows
    @Override
    public String sendEmailInfo(Warehouse warehouse, STATES states) {

        EmailNotification emailNotification;

        String recipient = restTemplate.getForObject(
                "http://localhost:" + CommunicationProperties.getUserLocalHostPort() +
                        "/users/email/{id}",
                String.class,
                warehouse.getOwnerid());

        emailNotification = CheckMessageInfoValidation.checkMessageState(states,warehouse);
        emailNotification.setRecipient(recipient);

        // you need to catch it's exceptions. for example, is there url?
        return restTemplate.getForObject(
                "http://localhost:"+CommunicationProperties.getNotificationLocalHostPort()+
                        "/emails/sendemail/info?recipient={recipient}" +
                        "&message={message}&subject={subject}",
                String.class,
                emailNotification.getRecipient(), emailNotification.getMessage(),
                emailNotification.getSubject());
    }

    @Override
    public String sendSmsInfo(Warehouse warehouse, STATES states) {

        SmsNotification smsNotification;

        String phoneNumber = restTemplate.getForObject(
                "http://localhost:"+CommunicationProperties.getUserLocalHostPort()+
                        "/users/sms/{id}",
                String.class,
                warehouse.getOwnerid());

        smsNotification = CheckMessageInfoValidation.checkSmsState(states,warehouse);
        smsNotification.setPhoneNumber(phoneNumber);

        return restTemplate.getForObject(
                "http://localhost:"+CommunicationProperties.getNotificationLocalHostPort()+
                        "/sms/sendsms?message={message}&phoneNumber={phoneNumber}",
                String.class,
                smsNotification.getMessage(),smsNotification.getPhoneNumber());
    }
}

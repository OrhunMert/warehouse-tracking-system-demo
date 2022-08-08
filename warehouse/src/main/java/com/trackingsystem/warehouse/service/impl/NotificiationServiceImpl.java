package com.trackingsystem.warehouse.service.impl;

import com.trackingsystem.warehouse.model.notification.EmailNotification;
import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.model.enums.STATES;
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
    public String sendEmailInfo(Warehouse warehouse, STATES states, String recipient) {

        EmailNotification emailNotification;
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
    public String sendSmsInfo(Warehouse warehouse, STATES states, String phoneNumber) {

        SmsNotification smsNotification;
        smsNotification = CheckMessageInfoValidation.checkSmsState(states,warehouse);
        smsNotification.setPhoneNumber(phoneNumber);

        return restTemplate.getForObject(
                "http://localhost:"+CommunicationProperties.getNotificationLocalHostPort()+
                        "/sms/sendsms?message={message}&phoneNumber={phoneNumber}",
                String.class,
                smsNotification.getMessage(),smsNotification.getPhoneNumber());
    }
}

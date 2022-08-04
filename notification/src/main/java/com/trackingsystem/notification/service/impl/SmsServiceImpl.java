package com.trackingsystem.notification.service.impl;

import com.trackingsystem.notification.dto.SmsDTO;
import com.trackingsystem.notification.exception.SmsUrlConnectionException;
import com.trackingsystem.notification.model.Sms;
import com.trackingsystem.notification.service.SmsService;
import com.trackingsystem.notification.utils.SenderProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final ModelMapper modelMapper;

    @Override
    public String sendSms(String message, String phoneNumber) {

        // You need to download GSM Modem(SMS) and GSM Helper Tool on your android device.

        // it will be changed when Json body was added.
        String username = SenderProperties.getAppUsername();
        String password = SenderProperties.getAppPassword();
        String address = SenderProperties.getMobileAppAddress();
        String port = SenderProperties.getMobileAppPort();

        return connectMobileDevice(message,phoneNumber,
                username,password,
                address,port);
    }

    @Override
    public String sendAllSms(SmsDTO smsDTO) {

        // You need to download GSM Modem(SMS) and GSM Helper Tool on your android device.

        Sms sms = modelMapper.map(smsDTO, Sms.class);

        String message = sms.getMessage();
        String phoneNumber = sms.getPhoneNumber();
        String username = SenderProperties.getAppUsername();
        String password = SenderProperties.getAppPassword();
        String address = SenderProperties.getMobileAppAddress();
        String port = SenderProperties.getMobileAppPort();

        return connectMobileDevice(message,phoneNumber,
                username,password,
                address,port);
    }

    @SneakyThrows
    @Override
    public String connectMobileDevice(String message,
                                      String phoneNumber,
                                      String username, String password,
                                      String address, String port) {

        String responseSms;

        try {
            URL url = new URL(
                    address+":"+port+"/SendSMS?username="+username+"&password="+password+
                            "&phone="+phoneNumber+"&message="+ URLEncoder.encode(message, StandardCharsets.UTF_8));

            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((responseSms = bufferedReader.readLine()) !=null){
                log.info(""+responseSms);
            }
            bufferedReader.close();

        } catch (MalformedURLException | UnsupportedEncodingException e) {
            throw new SmsUrlConnectionException("Error while sending sms from url!!!");
        }
        return responseSms;
    }
}

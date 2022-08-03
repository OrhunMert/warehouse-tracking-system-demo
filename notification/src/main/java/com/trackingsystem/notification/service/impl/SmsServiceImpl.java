package com.trackingsystem.notification.service.impl;

import com.trackingsystem.notification.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Override
    public String sendSms(String message, String phoneNumber) {

        // You need to download GSM Modem(SMS) and GSM Helper Tool on your android device.

        String username = "orhunmert77";
        String password = "446123";
        String address = "http://192.168.1.5";
        String port = "8090";

        String responseSms = "";

        try {
            URL url = new URL(
                     address+":"+port+"/SendSMS?username="+username+"&password="+password+
                             "&phone="+phoneNumber+"&message="+ URLEncoder.encode(message,"UTF-8"));

            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((responseSms = bufferedReader.readLine()) !=null){
                log.info(""+responseSms);
            }
            bufferedReader.close();

        } catch (MalformedURLException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return responseSms;
    }
}

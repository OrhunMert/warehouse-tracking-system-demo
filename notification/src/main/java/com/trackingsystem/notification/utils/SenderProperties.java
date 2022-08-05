package com.trackingsystem.notification.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SenderProperties {

    @Value("${spring.mail.username}") private static String mailSender;
    private final static String mobileAppAddress = "http://192.168.1.5";
    private final static String mobileAppPort = "8090";
    private final static String appUsername = "orhun";
    private final static String appPassword = "123";

    public static String getMailSender() {
        return mailSender;
    }

    public static String getMobileAppAddress() {
        return mobileAppAddress;
    }

    public static String getMobileAppPort() {
        return mobileAppPort;
    }

    public static String getAppUsername() {
        return appUsername;
    }

    public static String getAppPassword() {
        return appPassword;
    }
}
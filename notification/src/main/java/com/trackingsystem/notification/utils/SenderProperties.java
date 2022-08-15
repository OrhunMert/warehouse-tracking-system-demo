package com.trackingsystem.notification.utils;

public enum SenderProperties {;

    private final static String mailSender = "orhunombhuawei7@gmail.com";

    private final static String mobileAppAddress = "http://192.168.1.5";

    private final static String mobileAppPort = "8090";

    private final static String appUsername = "orhun";

    private final static String appPassword = "123";

    private final static String EMAIL_REGEX = "^(.+)@(.+).com";

    private final static String PHONE_REGEX = "05([0-9])([0-9])([0-9])([0-9])[0-9][0-9][0-9]([0-9]([0-9]))";

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

    public static String getEmailRegex() {return EMAIL_REGEX;}

    public static String getPhoneRegex() {return PHONE_REGEX;}
}

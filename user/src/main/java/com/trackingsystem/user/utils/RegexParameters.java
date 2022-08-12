package com.trackingsystem.user.utils;

public class RegexParameters {
     private final static String EMAIL_REGEX = "^(.+)@(.+).com";
     private final static String PHONE_REGEX = "05([0-9])([0-9])([0-9])([0-9])[0-9][0-9][0-9]([0-9]([0-9]))";
     public static String getEmailRegex() {
        return EMAIL_REGEX;
    }
     public static String getPhoneRegex(){
        return PHONE_REGEX;
    }
}
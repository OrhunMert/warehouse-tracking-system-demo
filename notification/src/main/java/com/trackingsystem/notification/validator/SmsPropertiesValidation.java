package com.trackingsystem.notification.validator;

import com.trackingsystem.notification.utils.SenderProperties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsPropertiesValidation {

    public static boolean checkSmsProperties(){
        if(SenderProperties.getAppPassword() == null | SenderProperties.getAppPassword().isBlank())
            return true;
        else if(SenderProperties.getAppUsername() == null | SenderProperties.getAppUsername().isBlank())
            return true;
        else if(SenderProperties.getMobileAppAddress() == null | SenderProperties.getMobileAppAddress().isBlank())
            return true;
        else if(SenderProperties.getMobileAppPort() == null | SenderProperties.getMobileAppPort().isBlank())
            return true;
        return false;
    }

    public static boolean checkPhoneValid(String phoneNumber){
        Pattern pattern = Pattern.compile(SenderProperties.getPhoneRegex());
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

}

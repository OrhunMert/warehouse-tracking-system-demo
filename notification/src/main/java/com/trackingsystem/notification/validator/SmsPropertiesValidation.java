package com.trackingsystem.notification.validator;

import com.trackingsystem.notification.utils.SenderProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
}

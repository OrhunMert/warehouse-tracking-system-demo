package com.trackingsystem.notification.validator;

import com.trackingsystem.notification.utils.SenderProperties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailPropertiesValidation {

    public static boolean checkEmailValid(String email){

        Pattern pattern = Pattern.compile(SenderProperties.getEmailRegex());
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

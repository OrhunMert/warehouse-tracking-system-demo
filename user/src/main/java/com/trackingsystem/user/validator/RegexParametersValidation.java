package com.trackingsystem.user.validator;

import com.trackingsystem.user.utils.RegexParameters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParametersValidation {

    public static boolean checkEmailValid(String email){

        Pattern pattern = Pattern.compile(RegexParameters.getEmailRegex());
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkPhoneValid(String phoneNumber){

        Pattern pattern = Pattern.compile(RegexParameters.getPhoneRegex());
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}

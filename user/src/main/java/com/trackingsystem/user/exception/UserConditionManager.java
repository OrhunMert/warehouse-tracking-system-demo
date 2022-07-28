package com.trackingsystem.user.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserConditionManager {

    public static void checkUsernameCondition(String username,Integer password){
        if(username.length()<4)
            throw new UserConditionException("username is not valid for length!!!");
        if(password.toString().length()<4)
            throw new UserConditionException("password is not valid for length!!!");
    }
    public static void checkEmailCondition(String email){

        if(email.isBlank())
            throw new UserConditionException("email is not valid. Its empty!!!");
        else if(email.indexOf('@')<=0)
            throw new UserConditionException("email is not valid. Mail's format is false!!!");
    }
    public static void checkPhoneNumber(String phoneNumber){
        // first gidit should be 0
        if(phoneNumber.indexOf('0')!=0)
            throw new UserConditionException("phone number is not valid. Phone Number's format is false");
        // phone number's length should be 10.
        else if(phoneNumber.length()!=11)
            throw new UserConditionException("phone number is not valid for length!!!");
    }


}

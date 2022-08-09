package com.trackingsystem.user.exception;
public class UserConditionManager {
    public static boolean checkUsernameCondition(String username,Integer password){
        if(username.length()<4)
            throw new UserConditionException("username is not valid for length!!!");
        else if(password.toString().length()<4)
            throw new UserConditionException("password is not valid for length!!!");
        return true;
    }
}

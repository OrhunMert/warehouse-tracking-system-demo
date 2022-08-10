package com.trackingsystem.user.validator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegexParametersValidationTest {
    @Test
    public void when_createUser_expect_EmailIsValid(){
        //given
        String email = "template@gmail.com";
        //when
        boolean actual = RegexParametersValidation.checkEmailValid(email);
        //then
        assertTrue(actual);
    }
    @Test
    public void when_createUser_expect_PhoneIsValid(){
        //given
        String phone = "05345678912";
        //when
        boolean actual = RegexParametersValidation.checkPhoneValid(phone);
        //then
        assertTrue(actual);
    }
}
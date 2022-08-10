package com.trackingsystem.notification.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class SmsPropertiesValidationTest {

    @Test
    public void when_sendSms_expect_HaveSmsProperties(){
        //given
        //when
        boolean actual = SmsPropertiesValidation.checkSmsProperties();
        //then
        assertFalse(actual);
    }
}
package com.trackingsystem.user.exception;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
public class UserConditionManagerTest {
    @Test
    public void when_loginUsernameAndPassword_expect_checkUsernameCondition(){
        //given
        String username= "orhunmert77";
        Integer password = 446123;
        //when
        boolean actual = UserConditionManager.checkUsernameCondition(username,password);
        //then
        assertTrue(actual);
    }
    @Test
    public void when_loginEmail_expect_checkEmailCondition(){
        //given
        String email = "orhun.bozkurt@huawei.com";
        //when
        boolean actual = UserConditionManager.checkEmailCondition(email);
        //then
        assertTrue(actual);
    }
    @Test
    public void when_loginPhoneNumber_expect_checkPhoneNumberCondition(){
        //given
        String phoneNumber = "05459780876";
        //when
        boolean actual = UserConditionManager.checkPhoneNumber(phoneNumber);
        //then
        assertTrue(actual);
    }
}
package com.trackingsystem.user.exception;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserConditionManagerTest {

    @Test
    public void when_loginUsernameandPassword_expect_checkUsernameCondition(){
        //given
        //when
        boolean isTrue = UserConditionManager.checkUsernameCondition("orhunmert77",446123);
        //then
        assertTrue(isTrue);
    }

    @Test
    public void when_loginEmail_expect_checkEmailCondition(){
        //given
        //when
        boolean isTrue = UserConditionManager.checkEmailCondition("orhun.bozkurt@huawei.com");
        //then
        assertTrue(isTrue);
    }

    @Test
    public void when_loginPhoneNumber_expect_checkPhoneNumberCondition(){
        //given
        //when
        boolean isTrue = UserConditionManager.checkPhoneNumber("05459780876");
        //then
        assertTrue(isTrue);
    }

}
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
}
package com.trackingsystem.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsDto {

    String message;
    String phoneNumber;
    String username;
    String password;
    String address;
    String port;

}


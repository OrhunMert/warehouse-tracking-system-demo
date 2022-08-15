package com.trackingsystem.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sms {

    String message;

    String phoneNumber;

    String username;

    String password;

    String address;

    String port;
}

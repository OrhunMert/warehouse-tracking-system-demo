package com.trackingsystem.warehouse.model.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsNotification {

    String phoneNumber;

    String message;

    String username;

    String password;

    String address;

    String port;
}

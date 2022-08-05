package com.trackingsystem.notification.dto;

import lombok.Data;

@Data
public class GetSmsDto {

    String message;
    String phoneNumber;

}

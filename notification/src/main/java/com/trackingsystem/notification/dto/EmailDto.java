package com.trackingsystem.notification.dto;

import lombok.Data;

@Data
public class EmailDto {

    private String recipient;

    private String message;

    private String subject;
}

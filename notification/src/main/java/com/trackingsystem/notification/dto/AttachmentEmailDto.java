package com.trackingsystem.notification.dto;

import lombok.Data;

@Data
public class AttachmentEmailDto {

    private String recipient;

    private String message;

    private String subject;

    private String attachment;
}

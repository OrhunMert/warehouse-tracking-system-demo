package com.trackingsystem.warehouse.model.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotification {
    String recipient;
    String message;
    String subject;
}

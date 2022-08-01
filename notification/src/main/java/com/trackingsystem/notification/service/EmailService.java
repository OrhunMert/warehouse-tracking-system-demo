package com.trackingsystem.notification.service;

import com.trackingsystem.notification.model.Email;

public interface EmailService {

    // replace Email class with EmailDTO
    String sendEmail(Email email);
    String sendMailWithAttachment(Email email);
    String sendEmailForEmptyWarehouse(String recipient,
                                      String message,
                                      String subject);
    String sendEmailForInfo(String recipient,
                            String message,
                            String subject);

}

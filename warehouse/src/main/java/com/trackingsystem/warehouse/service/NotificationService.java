package com.trackingsystem.warehouse.service;

import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.model.enums.STATES;
public interface NotificationService {
    void sendEmailInfo(Warehouse warehouse, STATES states, String recipient);
    void sendSmsInfo(Warehouse warehouse, STATES states, String phoneNumber); // it will can change with Json body this method's parameter
}

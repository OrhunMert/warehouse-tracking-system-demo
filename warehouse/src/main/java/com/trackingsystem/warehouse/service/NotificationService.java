package com.trackingsystem.warehouse.service;

import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.model.enums.States;

public interface NotificationService {

    void sendEmailInfo(Warehouse warehouse, States states, String recipient);

    void sendSmsInfo(Warehouse warehouse, States states, String phoneNumber); // it will can change with Json body this method's parameter
}

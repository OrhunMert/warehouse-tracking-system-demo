package com.trackingsystem.warehouse.service;

import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.model.enums.STATES;

public interface SendNotificationService {

    String sendEmailInfo(Warehouse warehouse, STATES states);
    String sendSmsInfo(Warehouse warehouse, STATES states); // it will can change with Json body this method's parameter

}

package com.trackingsystem.warehouse.validator;

import com.trackingsystem.warehouse.model.notification.EmailNotification;
import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.model.enums.States;
import com.trackingsystem.warehouse.model.notification.SmsNotification;
public class CheckMessageInfoValidation {
    public static EmailNotification checkMessageState(States states, Warehouse warehouse){

       EmailNotification emailNotification = new EmailNotification();

        if(states.equals(States.COMMON)){
             emailNotification.setMessage("Your warehouse's capacity is "+warehouse.getWarehouseCapacity()+
                    "\nWarehouse's current stock is "+warehouse.getCurrentStock()+
                    "\nWarehouse's total empty area is "+(warehouse.getWarehouseCapacity()-warehouse.getCurrentStock())+
                    " and Product number is "+warehouse.getProductList().size()+" in Warehouse");
             emailNotification.setSubject("About Warehouse Current State Information");
        }
        else if(states.equals(States.FULL)){
            emailNotification.setMessage("Your warehouse is full!!!");
            emailNotification.setSubject("Reminder about Warehouse");
        }
        else if(states.equals(States.EMPTY)){
            emailNotification.setMessage("Your warehouse is empty!!!");
            emailNotification.setSubject("Reminder about Warehouse");
        }
        return emailNotification;
    }
    public static SmsNotification checkSmsState(States states, Warehouse warehouse){

        SmsNotification smsNotification = new SmsNotification();

        if(states.equals(States.COMMON)){
            smsNotification.setMessage("Your warehouse's capacity is "+warehouse.getWarehouseCapacity()+
                    "\nWarehouse's current stock is "+warehouse.getCurrentStock()+
                    "\nWarehouse's total empty area is "+(warehouse.getWarehouseCapacity()-warehouse.getCurrentStock())+
                    " and Product number is "+warehouse.getProductList().size()+" in Warehouse");
        }
        else if(states.equals(States.FULL)){
            smsNotification.setMessage("Your warehouse is full!!!");
        }
        else if(states.equals(States.EMPTY)){
            smsNotification.setMessage("Your warehouse is empty!!!");
        }
        return smsNotification;
    }
}

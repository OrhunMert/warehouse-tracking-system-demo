package com.trackingsystem.warehouse.validator;

import com.trackingsystem.warehouse.dto.NotificationInfoDto;
import com.trackingsystem.warehouse.utils.CommunicationProperties;

import org.springframework.web.client.RestTemplate;
public class CommunicationNotificationValidation {
    public static NotificationInfoDto communicationFromWarehouseToUser(Long id){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                "http://localhost:" + CommunicationProperties.getUserLocalHostPort() +
                        "/users/notification/{id}",
                NotificationInfoDto.class, id);
    }
}

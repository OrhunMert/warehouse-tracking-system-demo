package com.trackingsystem.warehouse.exception;

import com.trackingsystem.warehouse.dto.NotificationInfoDto;
import com.trackingsystem.warehouse.model.Product;
import com.trackingsystem.warehouse.model.Warehouse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.client.RestTemplate;

import java.util.List;
@Slf4j
public class  WarehouseConditionException {
    public static boolean checkConditionToBuy(Warehouse warehouse, List<Product> productList) {
        if(productList.isEmpty())
            throw new ProductNotFoundException("Product not found to add to Warehouse");
        else if(!(productList.get(0).getProductgenre().equals(warehouse.getWarehouseGenre())))
            // you can setup the communication with notification service.(maybe)
            throw new WarehouseBusinessException("Warehouse's genre is not valid to add the Product!!!");
        else if(!(productList.get(0).getProductweight()
                + warehouse.getCurrentStock()<=warehouse.getWarehouseCapacity()))
            throw new WarehouseBusinessException("Warehouse's weight is not valid to add the Product!!!");
        return true;
    }
    public static boolean checkConditionToSell(Warehouse warehouse, List<Product> productList,String productName) {
        if(productList.isEmpty() | !warehouse.getProductList().contains(productName))
            throw new ProductNotFoundException("Product not found to sell from Warehouse");
        else if(!(productList.get(0).getProductgenre().equals(warehouse.getWarehouseGenre())))
            // you can setup the communication with notification service.(maybe)
            throw new WarehouseBusinessException("Warehouse's genre is not valid to sell the Product from Warehouse!!!");
        return true;
    }
    public static boolean checkHaveOwnerid(Warehouse warehouse, RestTemplate restTemplate){

        NotificationInfoDto getNotificationInfoDto = restTemplate.getForObject(
                "http://localhost:8080/users/{id}",
                NotificationInfoDto.class,
                warehouse.getOwnerid());

        if(getNotificationInfoDto == null){
            log.info("User not found");
            throw new UserNotFoundException("Ownerid not found by id in user table to create Warehouse");
        }
        log.info("User is found");
        return true;
    }
    public static boolean checkCapacityOfWarehouse(Integer warehouseCapacity,Integer currentStock){
        if(warehouseCapacity.equals(0))
            throw new WarehouseBusinessException("Warehouse' capacity shouldn't be equals 0");
        else if(warehouseCapacity < currentStock)
            throw new WarehouseBusinessException("Warehouse's capacity shouldn't be less than current stock");

        return true;
    }
}

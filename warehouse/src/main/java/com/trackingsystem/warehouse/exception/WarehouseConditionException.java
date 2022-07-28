package com.trackingsystem.warehouse.exception;

import com.trackingsystem.warehouse.model.Product;
import com.trackingsystem.warehouse.model.Warehouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.List;

@Slf4j
public class  WarehouseConditionException {

    public static void checkConditionToBuy(Warehouse warehouse, List<Product> productList) {
        if(productList.isEmpty())
            throw new UserNotFoundforWarehouseException("Product not found to add to Warehouse");
        else if(!(productList.get(0).getProductgenre().equals(warehouse.getWarehouseGenre())))
            // you can setup the communication with notification service.(maybe)
            throw new WarehouseBusinessException("Warehouse's genre is not valid to add the Product!!!");
        else if(!(productList.get(0).getProductweight()
                + warehouse.getCurrentStock()<=warehouse.getWarehouseCapacity()))
            throw new WarehouseBusinessException("Warehouse's weight is not valid to add the Product!!!");

    }
    public static void checkConditionToSell(Warehouse warehouse, List<Product> productList) {
        if(productList.isEmpty())
            throw new UserNotFoundforWarehouseException("Product not found to add to Warehouse");
        else if(!(productList.get(0).getProductgenre().equals(warehouse.getWarehouseGenre())))
            // you can setup the communication with notification service.(maybe)
            throw new WarehouseBusinessException("Warehouse's genre is not valid to add the Product!!!");
    }

    public static boolean checkHaveOwnerid(HttpStatus httpStatus){

        if(httpStatus.getReasonPhrase().equals(HttpStatus.NOT_FOUND.getReasonPhrase())){
            log.info("User not found");
            throw new UserNotFoundforWarehouseException("Ownerid not found by id in user table to create Warehouse");
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

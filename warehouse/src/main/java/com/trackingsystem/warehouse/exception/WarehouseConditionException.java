package com.trackingsystem.warehouse.exception;

import com.trackingsystem.warehouse.model.Product;
import com.trackingsystem.warehouse.model.Warehouse;

import java.util.List;

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
}

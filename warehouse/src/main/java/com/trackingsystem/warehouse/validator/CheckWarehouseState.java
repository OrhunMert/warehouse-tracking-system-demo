package com.trackingsystem.warehouse.validator;

import java.util.List;

public class CheckWarehouseState {

    public static boolean isEmptyWarehouse(Integer currentStock, List<String> productList){
        if(currentStock == 0 && productList.isEmpty()) return true;
        return false;
    }
    public static boolean isFullWarehouse(Integer currentStock, Integer warehouseCapacity){
        if(currentStock == warehouseCapacity) return true;
        return false;
    }

}

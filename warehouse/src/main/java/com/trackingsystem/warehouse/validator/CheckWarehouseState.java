package com.trackingsystem.warehouse.validator;

import java.util.List;

public class CheckWarehouseState {

    public boolean isEmptyWarehouse(Integer currentStock, List<String> productList){
        if(currentStock == 0 && productList.isEmpty()) return true;
        return false;
    }
    public boolean isFullWarehouse(Integer currentStock, Integer warehouseCapacity){
        if(currentStock == warehouseCapacity) return true;
        return false;
    }

}

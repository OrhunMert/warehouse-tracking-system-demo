package com.trackingsystem.warehouse.validator;

import java.util.List;
import java.util.Objects;

public class CheckWarehouseStateValidation {

    public static boolean isEmptyWarehouse(Integer currentStock, List<String> productList){
        return currentStock == 0 && productList.isEmpty();
    }

    public static boolean isFullWarehouse(Integer currentStock, Integer warehouseCapacity){
        return Objects.equals(currentStock, warehouseCapacity);
    }
}

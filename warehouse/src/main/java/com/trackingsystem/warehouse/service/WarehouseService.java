package com.trackingsystem.warehouse.service;

import com.trackingsystem.warehouse.dto.UpdateWarehouseDTO;
import com.trackingsystem.warehouse.dto.WarehouseDTO;
import com.trackingsystem.warehouse.model.Product;
import com.trackingsystem.warehouse.model.Warehouse;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;

public interface WarehouseService {
    Warehouse createWarehouse(WarehouseDTO warehouseDTO);
    Warehouse getWarehouse(Long id);
    Warehouse updateWarehouse(Long id,UpdateWarehouseDTO updateWarehouseDTO);
    void deleteWarehouse(Long id);
    List<String> buyProductForWarehouse(Long id,String productName);
    void checkConditionToBuy(Warehouse warehouse,List<Product> productList);
    HttpStatus sellProductForWarehouse(Long id,String productName);
    void checkConditionToSell(Warehouse warehouse,List<Product> productList);


}

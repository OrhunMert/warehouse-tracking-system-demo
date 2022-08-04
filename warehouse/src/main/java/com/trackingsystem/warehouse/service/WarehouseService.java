package com.trackingsystem.warehouse.service;

import com.trackingsystem.warehouse.dto.UpdateWarehouseDTO;
import com.trackingsystem.warehouse.dto.WarehouseDTO;
import com.trackingsystem.warehouse.model.Warehouse;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface WarehouseService {
    Warehouse createWarehouse(WarehouseDTO warehouseDTO);
    String getWarehouse(Long id);
    Warehouse updateWarehouse(Long id,UpdateWarehouseDTO updateWarehouseDTO);
    void deleteWarehouse(Long id);
    List<String> buyProduct(Long id,String productName);
    HttpStatus sellProduct(Long id,String productName);
}

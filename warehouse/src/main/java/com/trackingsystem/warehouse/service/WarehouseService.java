package com.trackingsystem.warehouse.service;

import com.trackingsystem.warehouse.dto.UpdateWarehouseDto;
import com.trackingsystem.warehouse.dto.WarehouseDto;
import com.trackingsystem.warehouse.model.Warehouse;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface WarehouseService {
    Warehouse createWarehouse(WarehouseDto warehouseDTO);
    String getWarehouse(Long id);
    Warehouse updateWarehouse(Long id, UpdateWarehouseDto updateWarehouseDTO);
    void deleteWarehouse(Long id);
    List<String> buyProduct(Long id,String productName);
    HttpStatus sellProduct(Long id,String productName);
}

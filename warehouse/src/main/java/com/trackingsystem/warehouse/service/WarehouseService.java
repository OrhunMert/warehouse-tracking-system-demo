package com.trackingsystem.warehouse.service;

import com.trackingsystem.warehouse.dto.WarehouseDTO;
import com.trackingsystem.warehouse.model.Warehouse;

public interface WarehouseService {
    Warehouse createWarehouse(WarehouseDTO warehouseDTO);
    Warehouse getWarehouse(Long id);
    Warehouse updateWarehouse(Long id,WarehouseDTO warehouseDTO);
    void deleteWarehouse(Long id);
}

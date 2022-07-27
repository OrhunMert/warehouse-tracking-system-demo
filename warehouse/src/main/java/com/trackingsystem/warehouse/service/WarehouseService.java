package com.trackingsystem.warehouse.service;

import com.trackingsystem.warehouse.dto.UpdateWarehouseDTO;
import com.trackingsystem.warehouse.dto.WarehouseDTO;
import com.trackingsystem.warehouse.model.Warehouse;

public interface WarehouseService {
    Warehouse createWarehouse(WarehouseDTO warehouseDTO);
    Warehouse getWarehouse(Long id);
    Warehouse updateWarehouse(Long id, UpdateWarehouseDTO updateWarehouseDTO);
    void deleteWarehouse(Long id);
    //it will be Set of Product for return type.
    void buyProductForWarehouse(Long id,String productName);

}

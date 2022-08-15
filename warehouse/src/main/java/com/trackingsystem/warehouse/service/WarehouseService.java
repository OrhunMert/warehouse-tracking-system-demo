package com.trackingsystem.warehouse.service;

import com.trackingsystem.warehouse.dto.WarehouseOperationDto;
import com.trackingsystem.warehouse.dto.UpdatedWarehouseDto;
import com.trackingsystem.warehouse.dto.WarehouseDto;

public interface WarehouseService {

    WarehouseDto createWarehouse(WarehouseDto warehouseDTO);

    WarehouseDto getWarehouse(Long id);

    UpdatedWarehouseDto updateWarehouse(Long id, UpdatedWarehouseDto updateWarehouseDTO);

    void deleteWarehouse(Long id);

    WarehouseOperationDto buyProduct(Long id, String productName);

    WarehouseOperationDto sellProduct(Long id, String productName);
}

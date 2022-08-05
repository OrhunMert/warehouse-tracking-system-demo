package com.trackingsystem.warehouse.service;

import com.trackingsystem.warehouse.dto.GetWarehouseToBuyDto;
import com.trackingsystem.warehouse.dto.GetWarehouseToSellDto;
import com.trackingsystem.warehouse.dto.UpdateWarehouseDto;
import com.trackingsystem.warehouse.dto.WarehouseDto;
public interface WarehouseService {
    WarehouseDto createWarehouse(WarehouseDto warehouseDTO);
    WarehouseDto getWarehouse(Long id);
    UpdateWarehouseDto updateWarehouse(Long id, UpdateWarehouseDto updateWarehouseDTO);
    void deleteWarehouse(Long id);
    GetWarehouseToBuyDto buyProduct(Long id, String productName);
    GetWarehouseToSellDto sellProduct(Long id, String productName);
}

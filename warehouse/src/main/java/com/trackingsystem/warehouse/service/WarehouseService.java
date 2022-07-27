package com.trackingsystem.warehouse.service;

import com.trackingsystem.warehouse.dto.UpdateWarehouseDTO;
import com.trackingsystem.warehouse.dto.WarehouseDTO;
import com.trackingsystem.warehouse.model.Product;
import com.trackingsystem.warehouse.model.Warehouse;

import java.util.List;
import java.util.Set;

public interface WarehouseService {
    Warehouse createWarehouse(WarehouseDTO warehouseDTO);
    Warehouse getWarehouse(Long id);
    Warehouse updateWarehouse(Long id, UpdateWarehouseDTO updateWarehouseDTO);
    void deleteWarehouse(Long id);
    //it will be Set of Product for return type.
    //List<Product> buyProductForWarehouse(Long id, String productName);
    List<String>buyProductForWarehouse(Long id,String productName);


}

package com.trackingsystem.warehouse.controller;

import com.trackingsystem.warehouse.dto.WarehouseOperationDto;
import com.trackingsystem.warehouse.dto.UpdatedWarehouseDto;
import com.trackingsystem.warehouse.dto.WarehouseDto;
import com.trackingsystem.warehouse.service.WarehouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouses")
@Api(value = "Warehouse Api")
public class WarehouseController {
    private final WarehouseService warehouseService;
    @PostMapping()
    @ApiOperation(value = "Create Warehouse for User. User should created previously. " +
            "Current Stock and Warehouse Capacity should be valid. and Capacity have to bigger than 0 and upper than current stock")
    ResponseEntity<WarehouseDto> createWarehouse(@Valid @RequestBody WarehouseDto warehouseDTO){
        return ResponseEntity.ok().body(warehouseService.createWarehouse(warehouseDTO));
    }
    @PostMapping("/buy")
    @ApiOperation(value = "buying operation for warehouse. You should add the same product's genre with warehouse's genre." +
            " if warehouse is being full While user is buying the product, System sends to notification as email and sms to user.")
    ResponseEntity<WarehouseOperationDto> buyProductForWarehouse(@RequestParam Long id,
                                                                 @RequestParam String productName){
        return ResponseEntity.ok().body(warehouseService.buyProduct(id,productName));
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Get Information about warehouse states. System sends to notification as email and sms to user")
    ResponseEntity<WarehouseDto> getWarehouse(@PathVariable Long id){
        return ResponseEntity.ok().body(warehouseService.getWarehouse(id));
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "Update information of warehouse")
    ResponseEntity<UpdatedWarehouseDto> updateWarehouse(@PathVariable Long id,
                                                        @Valid @RequestBody UpdatedWarehouseDto updateWarehouseDTO){
        return ResponseEntity.ok().body(warehouseService.updateWarehouse(id,updateWarehouseDTO));
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Warehouse")
    ResponseEntity<Object> deleteWarehouse(@PathVariable Long id){
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/sell")
    @ApiOperation(value = "selling operation for warehouse. You should remove the same product's genre with warehouse's genre."+
            " if warehouse is being empty While user is selling the product, System sends to notification as email and sms to user.")
    ResponseEntity<WarehouseOperationDto> sellProductForWarehouse(@RequestParam Long id,
                                                      @RequestParam String productName){
        return ResponseEntity.ok().body(warehouseService.sellProduct(id,productName));
    }
}

package com.trackingsystem.warehouse.controller;

import com.trackingsystem.warehouse.dto.WarehouseOperationDto;
import com.trackingsystem.warehouse.dto.UpdatedWarehouseDto;
import com.trackingsystem.warehouse.dto.WarehouseDto;
import com.trackingsystem.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
@Controller
@RequiredArgsConstructor
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping()
    ResponseEntity<WarehouseDto> createWarehouse(@Valid @RequestBody WarehouseDto warehouseDTO){
        return ResponseEntity.ok().body(warehouseService.createWarehouse(warehouseDTO));
    }

    @PostMapping("/buy")
    ResponseEntity<WarehouseOperationDto> buyProductForWarehouse(@RequestParam Long id,
                                                                 @RequestParam String productName){
        return ResponseEntity.ok().body(warehouseService.buyProduct(id,productName));
    }
    @GetMapping("/{id}")
    ResponseEntity<WarehouseDto> getWarehouse(@PathVariable Long id){
        return ResponseEntity.ok().body(warehouseService.getWarehouse(id));
    }
    @PutMapping("/{id}")
    ResponseEntity<UpdatedWarehouseDto> updateWarehouse(@PathVariable Long id,
                                                        @Valid @RequestBody UpdatedWarehouseDto updateWarehouseDTO){
        return ResponseEntity.ok().body(warehouseService.updateWarehouse(id,updateWarehouseDTO));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteWarehouse(@PathVariable Long id){
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/sell")
    ResponseEntity<WarehouseOperationDto> sellProductForWarehouse(@RequestParam Long id,
                                                      @RequestParam String productName){
        return ResponseEntity.ok().body(warehouseService.sellProduct(id,productName));
    }

}

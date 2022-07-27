package com.trackingsystem.warehouse.controller;

import com.trackingsystem.warehouse.dto.UpdateWarehouseDTO;
import com.trackingsystem.warehouse.dto.WarehouseDTO;
import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping()
    ResponseEntity<Warehouse> createWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO){
        return ResponseEntity.ok().body(warehouseService.createWarehouse(warehouseDTO));
    }

    @PostMapping("/buy")
    ResponseEntity<List<String>> buyProductForWarehouse(@RequestParam Long id,
                                                         @RequestParam String productName){
        return ResponseEntity.ok().body(warehouseService.buyProductForWarehouse(id,productName));
    }
    @GetMapping("/{id}")
    ResponseEntity<Warehouse> getWarehouse(@PathVariable Long id){
        return ResponseEntity.ok().body(warehouseService.getWarehouse(id));
    }
    @PutMapping("/{id}")
    ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id,
                                              @Valid @RequestBody UpdateWarehouseDTO updateWarehouseDTO){
        return ResponseEntity.ok().body(warehouseService.updateWarehouse(id,updateWarehouseDTO));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteWarehouse(@PathVariable Long id){
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}

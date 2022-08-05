package com.trackingsystem.warehouse.controller;

import com.trackingsystem.warehouse.dto.UpdateWarehouseDto;
import com.trackingsystem.warehouse.dto.WarehouseDto;
import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping()
    ResponseEntity<Warehouse> createWarehouse(@Valid @RequestBody WarehouseDto warehouseDTO){
        return ResponseEntity.ok().body(warehouseService.createWarehouse(warehouseDTO));
    }

    @PostMapping("/buy")
    ResponseEntity<List<String>> buyProductForWarehouse(@RequestParam Long id,
                                                         @RequestParam String productName){
        return ResponseEntity.ok().body(warehouseService.buyProduct(id,productName));
    }
    @GetMapping("/{id}")
    ResponseEntity<String> getWarehouse(@PathVariable Long id){
        return ResponseEntity.ok().body(warehouseService.getWarehouse(id));
    }
    @PutMapping("/{id}")
    ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id,
                                              @Valid @RequestBody UpdateWarehouseDto updateWarehouseDTO){
        return ResponseEntity.ok().body(warehouseService.updateWarehouse(id,updateWarehouseDTO));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteWarehouse(@PathVariable Long id){
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
    @DeleteMapping("/sell")
    ResponseEntity<HttpStatus> sellProductForWarehouse(@RequestParam Long id,
                                                       @RequestParam String productName){
        return ResponseEntity.ok().body(warehouseService.sellProduct(id,productName));
    }

}

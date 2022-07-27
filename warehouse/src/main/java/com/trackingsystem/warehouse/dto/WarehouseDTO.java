package com.trackingsystem.warehouse.dto;

import com.trackingsystem.warehouse.model.Genre;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WarehouseDTO {

    @NotBlank
    private String warehouseName;
    private Integer warehouseCapacity;
    private Integer currentStock;
    private Genre warehouseGenre;

}

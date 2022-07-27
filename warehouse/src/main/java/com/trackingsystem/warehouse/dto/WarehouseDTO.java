package com.trackingsystem.warehouse.dto;

import com.trackingsystem.warehouse.model.Genre;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WarehouseDTO {

    @NotNull
    private Long ownerid;
    @NotBlank
    private String warehouseName;
    private Integer warehouseCapacity;
    private Integer currentStock;
    private Genre warehouseGenre;

}
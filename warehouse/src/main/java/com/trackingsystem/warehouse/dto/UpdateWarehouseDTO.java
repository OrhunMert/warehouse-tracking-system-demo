package com.trackingsystem.warehouse.dto;

import com.trackingsystem.warehouse.model.enums.Genre;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateWarehouseDTO {

    @NotBlank
    private String warehouseName;
    private Integer warehouseCapacity;
    private Integer currentStock;
    @Enumerated(EnumType.STRING)
    private Genre warehouseGenre;
}

package com.trackingsystem.warehouse.dto;

import com.trackingsystem.warehouse.model.enums.Genre;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.util.ArrayList;
import java.util.List;

@Data
public class WarehouseOperationDto {
    private String warehouseName;
    private Integer warehouseCapacity;
    private Integer currentStock;
    @Enumerated(EnumType.STRING)
    private Genre warehouseGenre;
    @ElementCollection
    private List<String> productList = new ArrayList<>();
}

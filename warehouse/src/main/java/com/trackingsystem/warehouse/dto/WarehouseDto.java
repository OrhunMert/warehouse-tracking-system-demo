package com.trackingsystem.warehouse.dto;

import com.trackingsystem.warehouse.model.enums.Genre;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
@Data
public class WarehouseDto {
    @NotNull
    private Long ownerid;
    @NotBlank
    private String warehouseName;
    private Integer warehouseCapacity=100;
    private Integer currentStock=0;
    @Enumerated(EnumType.STRING)
    private Genre warehouseGenre;
    @ElementCollection
    private List<String> productList = new ArrayList<>();
}

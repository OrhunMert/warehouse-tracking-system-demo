package com.trackingsystem.warehouse.dto;

import com.trackingsystem.warehouse.model.Genre;
import com.trackingsystem.warehouse.model.Product;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class WarehouseDTO {

    @NotNull
    private Long ownerid;
    @NotBlank
    private String warehouseName;
    private Integer warehouseCapacity;
    private Integer currentStock;
    @Enumerated(EnumType.STRING)
    private Genre warehouseGenre;
    private List<Product> productSet = new ArrayList<>();


}

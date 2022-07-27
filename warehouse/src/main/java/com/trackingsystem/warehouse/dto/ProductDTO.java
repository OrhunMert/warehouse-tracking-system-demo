package com.trackingsystem.warehouse.dto;

import com.trackingsystem.warehouse.model.Genre;
import com.trackingsystem.warehouse.model.Warehouse;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {

    @NotNull
    private Genre productgenre;
    @NotBlank
    private String productname;
    private Integer productprice = 0;
    private Integer productweight = 0;
    private Warehouse warehouse;
}

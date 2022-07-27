package com.trackingsystem.warehouse.dto;

import com.trackingsystem.warehouse.model.Genre;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {

    @NotNull
    Genre productgenre;
    @NotBlank
    String productname;
    Integer productprice = 0;
    Integer productweight = 0;
}

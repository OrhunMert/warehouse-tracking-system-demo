package com.trackingsystem.warehouse.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "product",schema="trackingsystemwarehouse")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long idproduct;

    @NotNull
    Genre productgenre;
    @NotBlank
    String productname;
    Integer productprice = 0;
    Integer productweight = 0;

}

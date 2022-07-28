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
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre productgenre;
    @NotBlank
    private String productname;
    private Integer productprice = 0;
    private Integer productweight = 0;
    /*
    @ManyToOne
    private Warehouse warehouse;

     */

}

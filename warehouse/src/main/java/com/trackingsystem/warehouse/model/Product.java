package com.trackingsystem.warehouse.model;

import com.trackingsystem.warehouse.model.enums.Genre;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

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
}

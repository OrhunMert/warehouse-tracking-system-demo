package com.trackingsystem.warehouse.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "warehouse",schema = "trackingsystemwarehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private Long ownerid;
    private String warehouseName;
    private Integer warehouseCapacity=100;
    private Integer currentStock=0;

    @Enumerated(EnumType.STRING)
    private Genre warehouseGenre;

    @ElementCollection
    private List<String> productList = new ArrayList<>();

}

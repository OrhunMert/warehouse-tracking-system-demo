package com.trackingsystem.warehouse.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private Integer warehouseCapacity;
    private Integer currentStock;

    @Enumerated(EnumType.STRING)
    private Genre warehouseGenre;

}

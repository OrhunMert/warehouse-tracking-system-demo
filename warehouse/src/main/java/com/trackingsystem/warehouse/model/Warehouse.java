package com.trackingsystem.warehouse.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //we will change from set to list
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Product> productSet = new ArrayList<>();

}

package com.trackingsystem.warehouse.exception;

import com.trackingsystem.warehouse.model.enums.Genre;
import com.trackingsystem.warehouse.model.Product;
import com.trackingsystem.warehouse.model.Warehouse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class WarehouseConditionExceptionTest {

    private Warehouse warehouse;
    private Product product;
    private List<Product> productList;

    @Before
    public void setUp()  {
        warehouse = new Warehouse();
        product = new Product();
        productList = new ArrayList<>();
    }

    @Test
    public void when_buyProduct_excpet_checkConditionToBuy(){


        //given
        // they will do with map struct
        product.setProductgenre(Genre.FRUIT);
        product.setProductweight(10);
        productList.add(product);
        warehouse.setWarehouseGenre(Genre.FRUIT);
        warehouse.setWarehouseCapacity(100);
        warehouse.setCurrentStock(0);

        //when
        boolean actual = WarehouseConditionException.checkConditionToBuy(warehouse,productList);

        //then
        assertTrue(actual);

    }
    @Test
    public void when_sellProduct_except_checkConditionToSell(){

        //given
        // they will do with map struct
        product.setProductgenre(Genre.VEGATABLE);
        product.setProductweight(20);
        productList.add(product);
        warehouse.setWarehouseGenre(Genre.VEGATABLE);
        warehouse.setWarehouseCapacity(100);
        warehouse.setCurrentStock(80);

        //when
        boolean actual = WarehouseConditionException.checkConditionToSell(warehouse,productList);

        //then
        assertTrue(actual);

    }

    @Test
    public void when_createWarehouse_except_checkOwnerId(){

        //given
        Warehouse warehouse = new Warehouse();
        RestTemplate restTemplate = new RestTemplate();
        Long ownerId = Long.valueOf(1);
        warehouse.setOwnerid(ownerId);

        //when
        boolean actual = WarehouseConditionException.checkHaveOwnerid(warehouse,restTemplate);

        //then
        assertTrue(actual);

    }
    @Test
    public void when_createWarehouse_except_checkCapacity(){

        //given
        Integer warehouseCapacity = 100;
        Integer currentStock = 0;

        //when
        boolean actual = WarehouseConditionException.checkCapacityOfWarehouse(warehouseCapacity,currentStock);

        //then
        assertTrue(actual);

    }


}
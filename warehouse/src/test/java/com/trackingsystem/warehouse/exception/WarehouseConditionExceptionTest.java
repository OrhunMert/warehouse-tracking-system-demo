package com.trackingsystem.warehouse.exception;

import com.trackingsystem.warehouse.model.Genre;
import com.trackingsystem.warehouse.model.Product;
import com.trackingsystem.warehouse.model.Warehouse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

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
        boolean haveBuy = WarehouseConditionException.checkConditionToBuy(warehouse,productList);

        //then
        assertTrue(haveBuy);

    }
    @Test
    public void when_sellProduct_except_checkConditionToSell(){

        //given

        // they will do with map struct
        product.setProductgenre(Genre.FRUIT);
        product.setProductweight(10);

        productList.add(product);

        warehouse.setWarehouseGenre(Genre.FRUIT);
        warehouse.setWarehouseCapacity(100);
        warehouse.setCurrentStock(0);

        //when
        boolean haveSell = WarehouseConditionException.checkConditionToSell(warehouse,productList);
        //then
        assertTrue(haveSell);

    }

    @Test
    public void when_createWarehouse_except_checkOwnerId(){
        //given
        HttpStatus httpStatus1 = HttpStatus.ACCEPTED;
        HttpStatus httpStatus2 = HttpStatus.OK;

        //when
        boolean haveOwnerid1 = WarehouseConditionException.checkHaveOwnerid(httpStatus1);
        boolean haveOwnerid2 = WarehouseConditionException.checkHaveOwnerid(httpStatus2);

        //then
        assertTrue(haveOwnerid1);
        assertTrue(haveOwnerid2);

    }
    @Test
    public void when_createWarehouse_except_checkCapacity(){
        //given

        //when
        boolean validCapacity1 = WarehouseConditionException.checkCapacityOfWarehouse(100,0);
        boolean validCapacity2 = WarehouseConditionException.checkCapacityOfWarehouse(200,0);

        //then
        assertTrue(validCapacity1);
        assertTrue(validCapacity2);

    }


}
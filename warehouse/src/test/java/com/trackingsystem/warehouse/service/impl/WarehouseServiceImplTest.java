package com.trackingsystem.warehouse.service.impl;
import com.trackingsystem.warehouse.validator.CheckWarehouseStateValidation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class WarehouseServiceImplTest {

    private List<String> productList;
    @Before
    public void setUp()  {
        productList = new ArrayList<>();
    }
    @Test
    public void when_BuyProduct_expect_IsFull(){
        //given
        Integer currentStock = 100;
        Integer warehouseCapacity = 100;
        //when
        boolean actual = CheckWarehouseStateValidation.isFullWarehouse(currentStock,warehouseCapacity);
        //then
        assertTrue(actual);
    }
    @Test
    public void when_sellProduct_expect_IsEmpty(){
        //given
        Integer currentStock = 0;
        //when
        boolean actual = CheckWarehouseStateValidation.isEmptyWarehouse(currentStock,productList);
        //then
        assertTrue(actual);
    }
}
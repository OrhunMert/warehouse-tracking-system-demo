package com.trackingsystem.warehouse.service.impl;

import com.trackingsystem.warehouse.dto.*;
import com.trackingsystem.warehouse.exception.WarehouseConditionException;
import com.trackingsystem.warehouse.exception.WarehouseNotFoundException;

import com.trackingsystem.warehouse.model.Product;
import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.model.enums.States;

import com.trackingsystem.warehouse.repository.ProductRepository;
import com.trackingsystem.warehouse.repository.WarehouseRepository;

import com.trackingsystem.warehouse.service.NotificationService;
import com.trackingsystem.warehouse.service.WarehouseService;

import com.trackingsystem.warehouse.validator.CheckWarehouseStateValidation;
import com.trackingsystem.warehouse.validator.CommunicationNotificationValidation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private final ModelMapper modelMapper;

    private final RestTemplate restTemplate;

    private final WarehouseRepository warehouseRepository;

    private final ProductRepository productRepository;

    private final NotificationService sendNotificationService;

    @Override
    public WarehouseDto createWarehouse(WarehouseDto warehouseDTO) {

      Warehouse warehouse = modelMapper.map(warehouseDTO,Warehouse.class);

      WarehouseConditionException.checkHaveOwnerid(warehouse, restTemplate);
      WarehouseConditionException.checkCapacityOfWarehouse(warehouse.getWarehouseCapacity(),
              warehouse.getCurrentStock());

      warehouseRepository.save(warehouse);
      return modelMapper.map(warehouse,WarehouseDto.class);
    }

    @Override
    public WarehouseDto getWarehouse(Long id) {

        Warehouse warehouse = warehouseRepository
                .findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found by id to get"));

        WarehouseConditionException.checkHaveOwnerid(warehouse, restTemplate);
        // Communication with user service.
        NotificationInfoDto getNotificationInfoDto =
                CommunicationNotificationValidation.communicationFromWarehouseToUser(warehouse.getOwnerid());
        // send email for information about Warehouse's state
        sendNotificationService.sendEmailInfo(warehouse, States.COMMON,getNotificationInfoDto.getMail());

        // send sms for information about Warehouse's state(without sms'json body)
        sendNotificationService.sendSmsInfo(warehouse, States.COMMON,getNotificationInfoDto.getPhoneNumber());

        return modelMapper.map(warehouse,WarehouseDto.class);
    }

    @Override
    public UpdatedWarehouseDto updateWarehouse(Long id, UpdatedWarehouseDto updateWarehouseDTO) {

        Warehouse warehouse = warehouseRepository
                .findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found by id to update"));

        WarehouseConditionException.checkCapacityOfWarehouse(updateWarehouseDTO.getWarehouseCapacity(),
                updateWarehouseDTO.getCurrentStock());
        warehouse.setWarehouseName(updateWarehouseDTO.getWarehouseName());
        warehouse.setWarehouseCapacity(updateWarehouseDTO.getWarehouseCapacity());
        warehouse.setCurrentStock(updateWarehouseDTO.getCurrentStock());
        warehouse.setWarehouseGenre(updateWarehouseDTO.getWarehouseGenre());
        warehouseRepository.save(warehouse);
        return modelMapper.map(warehouse, UpdatedWarehouseDto.class);
    }

    @Override
    public void deleteWarehouse(Long id) {
        warehouseRepository
                .findById(id)
                .orElseThrow(
                        () -> new WarehouseNotFoundException("Warehouse not found by id to delete"));
        warehouseRepository.deleteById(id);
    }

    @Override
    public WarehouseOperationDto buyProduct(Long id, String productName) {

        Warehouse warehouse = warehouseRepository
                .findById(id)
                .orElseThrow(
                        ()->new WarehouseNotFoundException("Warehouse not found to buy product"));

        List<Product> productSet = productRepository.findByproductname(productName);

        WarehouseConditionException.checkConditionToBuy(warehouse,productSet);
        log.info("productName:"+productSet.get(0).getProductname());
        warehouse.getProductList().add(productSet.get(0).getProductname());
        warehouse.setProductList(warehouse.getProductList().stream()
                .sorted().collect(Collectors.toList()));
        log.info("productList in Warehouse:{}",warehouse.getProductList());
        warehouse.setCurrentStock(warehouse.getCurrentStock() +
                productSet.get(0).getProductweight());
        // notification to buy product
        if(CheckWarehouseStateValidation.isFullWarehouse(warehouse.getCurrentStock(),
                warehouse.getWarehouseCapacity())){
            // Communication with user service.
            NotificationInfoDto getNotificationInfoDto =
                    CommunicationNotificationValidation.communicationFromWarehouseToUser(warehouse.getOwnerid());

            // send email to buy operation of Warehouse
            sendNotificationService.sendEmailInfo(warehouse, States.FULL,getNotificationInfoDto.getMail());

            // send sms to buy operation of Warehouse(without sms'json body)
            sendNotificationService.sendSmsInfo(warehouse, States.FULL,getNotificationInfoDto.getPhoneNumber());
        }
        warehouseRepository.save(warehouse);
        return modelMapper.map(warehouse, WarehouseOperationDto.class);
    }

    @Override
    public WarehouseOperationDto sellProduct(Long id, String productName) {

        Warehouse warehouse = warehouseRepository
                .findById(id)
                .orElseThrow(
                        ()-> new WarehouseNotFoundException("Warehouse not found to buy product"));

        List<Product> productSet = productRepository.findByproductname(productName);

        WarehouseConditionException.checkConditionToSell(warehouse,productSet,productName);
        int productIndex = warehouse.getProductList().indexOf(productName);
        log.info("Number of input product:"+productIndex);
        warehouse.getProductList().remove(productIndex);
        warehouse.setProductList(warehouse.getProductList().stream().sorted().collect(Collectors.toList()));
        warehouse.setCurrentStock(warehouse.getCurrentStock() -
                productSet.get(0).getProductweight());

        if(CheckWarehouseStateValidation.isEmptyWarehouse(warehouse.getCurrentStock(),
                warehouse.getProductList())){
            // Communication with user service.
            NotificationInfoDto getNotificationInfoDto =
                    CommunicationNotificationValidation.communicationFromWarehouseToUser(warehouse.getOwnerid());

            // send email to sell operation of Warehouse
            sendNotificationService.sendEmailInfo(warehouse, States.EMPTY,getNotificationInfoDto.getMail());

            // send sms to sell operation of Warehouse(without sms'json body)
            sendNotificationService.sendSmsInfo(warehouse, States.EMPTY,getNotificationInfoDto.getPhoneNumber());
        }
        warehouseRepository.save(warehouse);
        return modelMapper.map(warehouse,WarehouseOperationDto.class);
    }
}

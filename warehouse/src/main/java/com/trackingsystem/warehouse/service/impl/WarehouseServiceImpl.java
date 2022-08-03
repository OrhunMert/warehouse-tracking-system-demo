package com.trackingsystem.warehouse.service.impl;

import com.trackingsystem.warehouse.dto.UpdateWarehouseDTO;
import com.trackingsystem.warehouse.dto.WarehouseDTO;
import com.trackingsystem.warehouse.exception.WarehouseConditionException;
import com.trackingsystem.warehouse.exception.WarehouseNotFoundException;
import com.trackingsystem.warehouse.model.Product;
import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.repository.ProductRepository;
import com.trackingsystem.warehouse.repository.WarehouseRepository;
import com.trackingsystem.warehouse.service.WarehouseService;
import com.trackingsystem.warehouse.validator.CheckWarehouseState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;
    private static HttpStatus httpStatus;
    private String recipient;
    private String message;
    private String subject;

    @Override
    public Warehouse createWarehouse(WarehouseDTO warehouseDTO) {
      Warehouse warehouse = modelMapper.map(warehouseDTO,Warehouse.class);

      httpStatus = restTemplate.getForObject(
              "http://localhost:8080/users/check/{id}"
              ,HttpStatus.class
              ,warehouse.getOwnerid());

      assert httpStatus != null;
      WarehouseConditionException.checkHaveOwnerid(httpStatus);
      WarehouseConditionException.checkCapacityOfWarehouse(warehouse.getWarehouseCapacity(),
              warehouse.getCurrentStock());

      warehouseRepository.save(warehouse);

      return warehouse;
    }

    @Override
    public String getWarehouse(Long id) {
        Warehouse warehouse = warehouseRepository
                .findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found by id to get"));

        httpStatus = restTemplate.getForObject(
                "http://localhost:8080/users/check/{id}",
                HttpStatus.class,
                warehouse.getOwnerid());

        assert httpStatus != null;
        WarehouseConditionException.checkHaveOwnerid(httpStatus);

        // send email for information about Warehouse's state
        recipient = restTemplate.getForObject(
                "http://localhost:8080/users/email/{id}",
                String.class,
                warehouse.getOwnerid());

        message = "Your warehouse's capacity is "+warehouse.getWarehouseCapacity()+
                    "\nWarehouse's current stock is "+warehouse.getCurrentStock()+
                    "\nWarehouse's total empty area is "+(warehouse.getWarehouseCapacity()-warehouse.getCurrentStock())+
                    " and Product number is "+warehouse.getProductList().size()+" in Warehouse";

        subject = "About Warehouse Current State Information";
        sendEmailInfo(recipient,message,subject);

        // send sms for information about Warehouse's state(without sms'json body)
        String phoneNumber = restTemplate.getForObject(
                "http://localhost:8080/users/sms/{id}",
                String.class,
                warehouse.getOwnerid());

        sendSmsInfo(message,phoneNumber);

        return "Mail and SMS sent to user successfully";
    }

    @Override
    public Warehouse updateWarehouse(Long id, UpdateWarehouseDTO updateWarehouseDTO) {
        Warehouse warehouse = warehouseRepository
                .findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found by id to update"));

        warehouse.setWarehouseName(updateWarehouseDTO.getWarehouseName());
        warehouse.setWarehouseCapacity(updateWarehouseDTO.getWarehouseCapacity());
        warehouse.setCurrentStock(updateWarehouseDTO.getCurrentStock());
        warehouse.setWarehouseGenre(updateWarehouseDTO.getWarehouseGenre());
        warehouseRepository.save(warehouse);

        return warehouse;
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
    public List<String> buyProduct(Long id, String productName) {
        Warehouse warehouse = warehouseRepository
                .findById(id)
                .orElseThrow(
                        ()->new WarehouseNotFoundException("Warehouse not found to buy product"));

        List<Product> productSet = productRepository.findByproductname(productName);

        WarehouseConditionException.checkConditionToBuy(warehouse,productSet);
        log.info("productName:"+productSet.get(0).getProductname());
        warehouse.getProductList().add(productSet.get(0).getProductname());
        log.info("productList in Warehouse:{}",warehouse.getProductList());
        warehouse.setProductList(warehouse.getProductList());
        warehouse.setCurrentStock(warehouse.getCurrentStock() +
                productSet.get(0).getProductweight());

        // notification to buy product
        if(CheckWarehouseState.isFullWarehouse(warehouse.getCurrentStock(),
                warehouse.getWarehouseCapacity())){

            // send email to buy operation of Warehouse
            recipient = restTemplate.getForObject(
                    "http://localhost:8080/users/email/{id}",
                    String.class,
                    warehouse.getOwnerid());

            message = "Your warehouse is full!!!";
            subject = "Reminder about Warehouse";
            sendEmailInfo(recipient,message,subject);

            // send sms to buy operation of Warehouse(without sms'json body)
            String phoneNumber = restTemplate.getForObject(
                    "http://localhost:8080/users/sms/{id}",
                    String.class,
                    warehouse.getOwnerid());
            sendSmsInfo(message,phoneNumber);
        }

        warehouseRepository.save(warehouse);

        return warehouse.getProductList();

    }

    @Override
    public HttpStatus sellProduct(Long id, String productName) {
        Warehouse warehouse = warehouseRepository
                .findById(id)
                .orElseThrow(
                        ()->new WarehouseNotFoundException("Warehouse not found to buy product"));

        List<Product> productSet = productRepository.findByproductname(productName);

        WarehouseConditionException.checkConditionToSell(warehouse,productSet);
        int productIndex = warehouse.getProductList().indexOf(productName);
        log.info("Number of input product:"+productIndex);
        warehouse.getProductList().remove(productIndex);
        warehouse.setProductList(warehouse.getProductList());
        warehouse.setCurrentStock(warehouse.getCurrentStock() -
                productSet.get(0).getProductweight());

        if(CheckWarehouseState.isEmptyWarehouse(warehouse.getCurrentStock(),
                warehouse.getProductList())){

            // send email to sell operation of Warehouse
            recipient = restTemplate.getForObject(
                    "http://localhost:8080/users/email/{id}",
                    String.class,
                    warehouse.getOwnerid());
            message = "Your warehouse is empty!!!";
            subject = "Reminder about Warehouse";
            sendEmailInfo(recipient,message,subject);

            // send sms to sell operation of Warehouse(without sms'json body)
            String phoneNumber = restTemplate.getForObject(
                    "http://localhost:8080/users/sms/{id}",
                    String.class,
                    warehouse.getOwnerid());
            sendSmsInfo(message,phoneNumber);
        }

        warehouseRepository.save(warehouse);

        return HttpStatus.OK;
    }

    @Override
    public String sendEmailInfo(String recipient, String message, String subject) {
        return restTemplate.getForObject(
                "http://localhost:8082/emails/sendemail/info?recipient={recipient}" +
                        "&message={message}&subject={subject}",
                String.class,recipient,message,subject);
    }

    @Override
    public String sendSmsInfo(String message, String phoneNumber) {
        return restTemplate.getForObject(
                "http://localhost:8082/sms/sendsms?message={message}&phoneNumber={phoneNumber}",
                String.class,
                message,phoneNumber);
    }

}

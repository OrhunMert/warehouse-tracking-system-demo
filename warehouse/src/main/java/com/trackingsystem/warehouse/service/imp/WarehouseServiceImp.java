package com.trackingsystem.warehouse.service.imp;

import com.trackingsystem.warehouse.dto.UpdateWarehouseDTO;
import com.trackingsystem.warehouse.dto.WarehouseDTO;
import com.trackingsystem.warehouse.exception.UserNotFoundforWarehouseException;
import com.trackingsystem.warehouse.exception.WarehouseNotFoundException;
import com.trackingsystem.warehouse.model.Product;
import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.repository.ProductRepository;
import com.trackingsystem.warehouse.repository.WarehouseRepository;
import com.trackingsystem.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImp implements WarehouseService {

    private final ModelMapper modelMapper;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    @Override
    public Warehouse createWarehouse(WarehouseDTO warehouseDTO) {
      Warehouse warehouse = modelMapper.map(warehouseDTO,Warehouse.class);

      HttpStatus httpStatus = restTemplate.getForObject(
              "http://localhost:8080/users/check/{id}"
              ,HttpStatus.class
              ,warehouse.getOwnerid());


      log.info("Httpstatus Result:"+httpStatus);

      if(httpStatus.getReasonPhrase() == HttpStatus.NOT_FOUND.getReasonPhrase())
      {
          log.info("User not found");
          throw new UserNotFoundforWarehouseException("Ownerid not found by id in user table to create Warehouse");
      }

      else log.info("User is found");

      warehouseRepository.save(warehouse);
      return warehouse;
    }

    @Override
    public Warehouse getWarehouse(Long id) {
        return warehouseRepository
                .findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found by id to get"));
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
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found by id to delete"));
        warehouseRepository.deleteById(id);
    }

    @Override
    public List<Product> buyProductForWarehouse(Long id, String productName) {
        Warehouse warehouse = warehouseRepository
                .findById(id)
                .orElseThrow(()->new WarehouseNotFoundException("Warehouse not found to buy product"));

        //we will add to exception handling for not found exception
        List<Product> productSet = productRepository.findByproductname(productName);
        log.info("productSet:{}",productSet);


        return productSet;

    }
}

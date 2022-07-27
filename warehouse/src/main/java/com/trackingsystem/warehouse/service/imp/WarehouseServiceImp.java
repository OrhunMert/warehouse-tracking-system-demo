package com.trackingsystem.warehouse.service.imp;

import com.trackingsystem.warehouse.dto.WarehouseDTO;
import com.trackingsystem.warehouse.model.Warehouse;
import com.trackingsystem.warehouse.repository.WarehouseRepository;
import com.trackingsystem.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImp implements WarehouseService {

    private final ModelMapper modelMapper;
    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse createWarehouse(WarehouseDTO warehouseDTO) {
      Warehouse warehouse = modelMapper.map(warehouseDTO,Warehouse.class);
      warehouseRepository.save(warehouse);
      return warehouse;
    }

    @Override
    public Warehouse getWarehouse(Long id) {
        return warehouseRepository.findById(id).orElseThrow();
    }

    @Override
    public Warehouse updateWarehouse(Long id, WarehouseDTO warehouseDTO) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow();

        warehouse.setWarehouseName(warehouseDTO.getWarehouseName());
        warehouse.setWarehouseCapacity(warehouseDTO.getWarehouseCapacity());
        warehouse.setCurrentStock(warehouseDTO.getCurrentStock());
        warehouse.setWarehouseGenre(warehouseDTO.getWarehouseGenre());

        warehouseRepository.save(warehouse);

        return warehouse;
    }

    @Override
    public void deleteWarehouse(Long id) {
        warehouseRepository.findById(id).orElseThrow();
        warehouseRepository.deleteById(id);
    }
}

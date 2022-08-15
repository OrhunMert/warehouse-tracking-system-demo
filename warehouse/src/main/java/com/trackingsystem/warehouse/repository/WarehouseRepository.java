package com.trackingsystem.warehouse.repository;

import com.trackingsystem.warehouse.model.Warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}

package com.trackingsystem.warehouse.repository;

import com.trackingsystem.warehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByproductname(String productName);
}

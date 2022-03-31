package com.example.ordersdelivery.repository;

import com.example.ordersdelivery.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

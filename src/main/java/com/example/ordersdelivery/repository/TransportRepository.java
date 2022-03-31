package com.example.ordersdelivery.repository;

import com.example.ordersdelivery.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, Long> {
}

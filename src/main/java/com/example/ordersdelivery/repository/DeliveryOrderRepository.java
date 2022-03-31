package com.example.ordersdelivery.repository;

import com.example.ordersdelivery.entity.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
}

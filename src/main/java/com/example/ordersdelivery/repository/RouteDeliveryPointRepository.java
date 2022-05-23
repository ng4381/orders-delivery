package com.example.ordersdelivery.repository;

import com.example.ordersdelivery.entity.RouteDeliveryPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface RouteDeliveryPointRepository extends JpaRepository<RouteDeliveryPoint, Long> {
    List<RouteDeliveryPoint> getRouteDeliveryPointByRouteId(Long id);
}

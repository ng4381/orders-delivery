package com.example.ordersdelivery.service;

import com.example.ordersdelivery.entity.*;
import com.example.ordersdelivery.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RouteDetailServiceTest {
    @Autowired
    RouteDetailService routeDetailService;

    @Autowired
    TransportRepository transportRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    RouteDetailRepository routeDetailRepository;

    @Autowired
    DeliveryOrderDetailRepository deliveryOrderDetailRepository;

    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @Test
    void getRouteDetailByRouteId() {

        Transport transport = new Transport();
        transportRepository.save(transport);

        Route route_1 = new Route();
        route_1.setTransport(transport);
        routeRepository.save(route_1);

        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrderRepository.save(deliveryOrder);

        DeliveryOrderDetail deliveryOrderDetail = new DeliveryOrderDetail();
        deliveryOrderDetail.setDeliveryOrder(deliveryOrder);
        deliveryOrderDetail.setProduct_id(1L);
        deliveryOrderDetail.setQty(1);
        deliveryOrderDetailRepository.save(deliveryOrderDetail);

        RouteDetail routeDetail_1 = new RouteDetail();
        routeDetail_1.setDeliveryOrderDetail(deliveryOrderDetail);
        routeDetail_1.setRoute(route_1);
        routeDetailRepository.save(routeDetail_1);

        RouteDetail routeDetail_2 = new RouteDetail();
        routeDetail_2.setDeliveryOrderDetail(deliveryOrderDetail);
        routeDetail_2.setRoute(route_1);
        routeDetailRepository.save(routeDetail_2);

        List<RouteDetail> routeDetails = routeDetailService.getRouteDetailByRouteId(route_1.getId());
        assertEquals(2, routeDetails.size());
    }
}
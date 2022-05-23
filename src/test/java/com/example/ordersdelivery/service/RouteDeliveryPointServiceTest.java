package com.example.ordersdelivery.service;

import com.example.ordersdelivery.OrdersDeliveryApplication;
import com.example.ordersdelivery.entity.Route;
import com.example.ordersdelivery.entity.RouteDeliveryPoint;
import com.example.ordersdelivery.entity.Transport;
import com.example.ordersdelivery.repository.RouteDeliveryPointRepository;
import com.example.ordersdelivery.repository.RouteRepository;
import com.example.ordersdelivery.repository.TransportRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = OrdersDeliveryApplication.class)
class RouteDeliveryPointServiceTest {

    @Autowired
    RouteDeliveryPointService routeDeliveryPointService;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    RouteDeliveryPointRepository routeDeliveryPointRepository;

    @Autowired
    TransportRepository transportRepository;

    @Test
    void getRouteDeliveryPointByRouteId() {

        List<String> routeDeliveryPointsList = new ArrayList<>();

        routeDeliveryPointsList.add("Point-1");
        routeDeliveryPointsList.add("Point-2");
        routeDeliveryPointsList.add("Point-3");

        Transport transport = new Transport();
        transportRepository.save(transport);


        Route route_1 = new Route();
        route_1.setTransport(transport);
        Route saved_route_1 = routeRepository.save(route_1);

        routeDeliveryPointsList.forEach(deliveryPoint -> {
            RouteDeliveryPoint routeDeliveryPoint = new RouteDeliveryPoint();
            routeDeliveryPoint.setDelivery_to(deliveryPoint);
            routeDeliveryPoint.setRoute(saved_route_1);
            routeDeliveryPointRepository.save(routeDeliveryPoint);
        });


        List<RouteDeliveryPoint> routeDeliveryPoints = routeDeliveryPointService.getRouteDeliveryPointByRouteId(saved_route_1.getId());
        List<String> routeDeliveryPointsList_H2 =  routeDeliveryPoints.stream().map(routeDeliveryPoint -> routeDeliveryPoint.getDelivery_to()).collect(Collectors.toList());
        assertArrayEquals(routeDeliveryPointsList.toArray(), routeDeliveryPointsList_H2.toArray());
    }
}
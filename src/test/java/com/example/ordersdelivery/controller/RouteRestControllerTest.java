package com.example.ordersdelivery.controller;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.ordersdelivery.entity.*;
import com.example.ordersdelivery.exception.RouteDeliveryPointsIsNotEmptyException;
import com.example.ordersdelivery.exception.RouteDetailsIsNotEmptyException;
import com.example.ordersdelivery.repository.*;
import com.example.ordersdelivery.service.RouteDetailService;
import com.example.ordersdelivery.service.RouteService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RouteRestControllerTest {

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
    @Autowired
    RouteDeliveryPointRepository routeDeliveryPointRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deleteRouteTest() throws Exception {
        Transport transport = new Transport();
        transportRepository.save(transport);

        Route route_1 = new Route();
        route_1.setTransport(transport);
        routeRepository.save(route_1);

        this.mockMvc.perform(post("/routes/delete/" + route_1.getId())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteRouteWithRouteDetailsTest() throws Exception {
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

        this.mockMvc.perform(post("/routes/delete/" + route_1.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RouteDetailsIsNotEmptyException));
    }


    @Test
    void deleteRouteWithDeliveryPointsTest() throws Exception {
        Transport transport = new Transport();
        transportRepository.save(transport);

        Route route_1 = new Route();
        route_1.setTransport(transport);
        routeRepository.save(route_1);

        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrderRepository.save(deliveryOrder);

        RouteDeliveryPoint routeDeliveryPoint = new RouteDeliveryPoint();
        routeDeliveryPoint.setRoute(route_1);
        routeDeliveryPointRepository.save(routeDeliveryPoint);

        this.mockMvc.perform(post("/routes/delete/" + route_1.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RouteDeliveryPointsIsNotEmptyException));
    }


    @Test
    void getRoutesList() throws Exception {
        this.mockMvc.perform(get("/routes")).andDo(print()).andExpect(status().isOk());
    }

}
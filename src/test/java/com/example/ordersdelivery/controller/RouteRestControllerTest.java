package com.example.ordersdelivery.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.ordersdelivery.dto.RouteDTO;
import com.example.ordersdelivery.dto.RouteDeliveryPointDTO;
import com.example.ordersdelivery.dto.RouteDescDTO;
import com.example.ordersdelivery.entity.*;
import com.example.ordersdelivery.exception.RouteDeliveryPointsIsNotEmptyException;
import com.example.ordersdelivery.exception.RouteDetailsIsNotEmptyException;
import com.example.ordersdelivery.repository.*;
import com.example.ordersdelivery.service.RouteDeliveryPointService;
import com.example.ordersdelivery.service.RouteDetailService;
import com.example.ordersdelivery.service.RouteService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = RouteRestController.class)
class RouteRestControllerTest {

    @MockBean
    private RouteService routeService;
    @MockBean
    private RouteDetailService routeDetailService;
    @MockBean
    private RouteDeliveryPointService routeDeliveryPointService;

    @Autowired
    private MockMvc mockMvc;

    /*
    @Test
    @DisplayName("Delete route if details or points are empty")
    void deleteRouteTest() throws Exception {
        Transport transport = new Transport();
        transportRepository.save(transport);

        Route route_1 = new Route();
        route_1.setTransport(transport);
        routeRepository.save(route_1);

        this.mockMvc.perform(post("/routes/delete/" + route_1.getId())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("If route has a details then throw exception and don't delete")
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
    @DisplayName("If route has delivery points then throw exception and don't delete")
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


     */

    @Test
    @DisplayName("Get route list")
    void shouldGetRoutesList() throws Exception {
        this.mockMvc.perform(get("/routes")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get route description")
    public void shouldGetRouteDesc() throws Exception {

        LocalDate ETA = LocalDate.of(2022, 5,24);
        LocalDate ETD = LocalDate.of(2022, 5,24);

        RouteDescDTO routeDescDTO = RouteDescDTO.builder()
                .routeId(1L)
                .transportId(1L)
                .transportType("Type-1")
                .from("Storage-1")
                .ETA(ETA)
                .ETD(ETD)
                .build();

        Mockito.when(routeService.getRouteDesc(1L)).thenReturn(routeDescDTO);

        this.mockMvc.perform(get("/routes/desc/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.routeId", Matchers.is(1)))
                .andExpect(jsonPath("$.transportId", Matchers.is(1)))
                .andExpect(jsonPath("$.transportType", Matchers.is("Type-1")))
                .andExpect(jsonPath("$.from", Matchers.is("Storage-1")))
                .andExpect(jsonPath("$.eta", Matchers.is("2022-05-24")))
                .andExpect(jsonPath("$.etd", Matchers.is("2022-05-24")));

    }

    @Test
    @DisplayName("Should return list of routes")
    public void shouldReturnRouteDTOs() throws Exception {

        RouteDTO routeDTO_1 = RouteDTO.builder()
                .routeId(1L)
                .transportId(1L)
                .transportType("Type-1")
                .transportVolume(80)
                .totalLoadVolume(50)
                .transportVolumeRemain(30)
                .routeDetails(new ArrayList<>())
                .routeDeliveryPoints(new ArrayList<>())
                .build();

        RouteDTO routeDTO_2 = RouteDTO.builder()
                .routeId(2L)
                .transportId(1L)
                .transportType("Type-1")
                .transportVolume(80)
                .totalLoadVolume(10)
                .transportVolumeRemain(70)
                .routeDetails(new ArrayList<>())
                .routeDeliveryPoints(new ArrayList<>())
                .build();

        Mockito.when(routeDetailService.getAllRouteDetailsGroupedByRoutes())
                .thenReturn(List.of(routeDTO_1, routeDTO_2));

        this.mockMvc.perform(get("/routes/details"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].routeId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].transportId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].transportType", Matchers.is("Type-1")))
                .andExpect(jsonPath("$[0].transportVolume", Matchers.is(80.0)))
                .andExpect(jsonPath("$[0].totalLoadVolume", Matchers.is(50.0)))
                .andExpect(jsonPath("$[0].transportVolumeRemain", Matchers.is(30.0)))
                .andExpect(jsonPath("$[0].routeDetails.size()", Matchers.is(0)))
                .andExpect(jsonPath("$[0].routeDeliveryPoints.size()", Matchers.is(0)));
        ;
    }

    @Test
    @DisplayName("Should create a new route")
    public void shouldCreateRoute() throws Exception {
        this.mockMvc.perform(post("/routes/create"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should add route detail")
    public void shoudAddRouteDetail() throws Exception {
        this.mockMvc.perform(post("/routes/details/1/1/5"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return route delivery point list by route_id")
    public void shouldReturnRouteDeliveryPointDTOListByRouteId() throws Exception {
        RouteDeliveryPointDTO deliveryPointDTO_1 = RouteDeliveryPointDTO.builder()
                .id(1L)
                .route_id(1L)
                .delivery_to("address-1")
                .build();

        RouteDeliveryPointDTO deliveryPointDTO_2 = RouteDeliveryPointDTO.builder()
                .id(2L)
                .route_id(1L)
                .delivery_to("address-2")
                .build();

        Mockito.when(routeDeliveryPointService.getRouteDeliveryPointDTOList(1L))
                .thenReturn(List.of(deliveryPointDTO_1, deliveryPointDTO_2));

        this.mockMvc.perform(get("/routes/route-points/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", Matchers.is(2)));
    }

    @Test
    @DisplayName("Should create route delivery point")
    public void shouldCreateRouteDeliveryPoint() throws Exception {
        /*
        Mockito.doNothing().when(routeDeliveryPointService)
                .createRouteDeliveryPoint(new RouteDeliveryPointDTO());


        this.mockMvc.perform(post("/routes/route-points", new RouteDeliveryPointDTO()))
                .andExpect(status().isOk());

         */
    }

    @Test
    @DisplayName("Delete route delivery point by id")
    public void shouldDeleteRouteDeliveryPoint() throws Exception {
        this.mockMvc.perform(post("/routes/route-points/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should delete route by id")
    public void shouldDeleteRoute() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/routes/delete/1"))
                .andExpect(status().isOk());
    }
}
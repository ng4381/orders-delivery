package com.example.ordersdelivery.controller;

import com.example.ordersdelivery.dto.RouteDTO;
import com.example.ordersdelivery.dto.RouteDetailsDTO;
import com.example.ordersdelivery.entity.Route;
import com.example.ordersdelivery.entity.RouteDetail;
import com.example.ordersdelivery.service.RouteDetailService;
import com.example.ordersdelivery.service.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RouteRestController {
    private final RouteService routeService;
    private final RouteDetailService routeDetailService;

    @GetMapping("/routes")
    public List<Route> getRoutesList() {
        return routeService.getRoutesList();
    }

    @GetMapping("/routes/details")
    public List<RouteDTO> getRoutesDetailList() {
        return routeDetailService.getAllRouteDetailsGroupedByRoutes();
    }

    @PostMapping("/routes/create")
    public ResponseEntity<HttpStatus> createRoute() {
        routeService.createRoute();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/routes/details/{routeId}/{deliveryOrderDetailId}/{qty}")
    public ResponseEntity<HttpStatus> addRouteDetail(@PathVariable Long routeId, @PathVariable Long deliveryOrderDetailId, @PathVariable int qty) {
        routeDetailService.addRouteDetail(routeId, deliveryOrderDetailId, qty);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/route/test")
    public RouteDetail getRouteDetail(){
        return routeDetailService.getByRouterIdAndDeliveryOrderDetailId(100L,136L);
    }

}

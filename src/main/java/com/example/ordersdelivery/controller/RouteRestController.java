package com.example.ordersdelivery.controller;

import com.example.ordersdelivery.dto.RouteDTO;
import com.example.ordersdelivery.dto.RouteDeliveryPointDTO;
import com.example.ordersdelivery.entity.Route;
import com.example.ordersdelivery.entity.RouteDeliveryPoint;
import com.example.ordersdelivery.service.RouteDeliveryPointService;
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
    private final RouteDeliveryPointService routeDeliveryPointService;

    @GetMapping("/routes")
    public List<Route> getRoutesList() {
        return routeService.getRoutesList();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/routes/details")
    public List<RouteDTO> getRoutesDetailList() {
        return routeDetailService.getAllRouteDetailsGroupedByRoutes();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/routes/create")
    public ResponseEntity<HttpStatus> createRoute() {
        routeService.createRoute();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/routes/details/{routeId}/{deliveryOrderDetailId}/{qty}")
    public ResponseEntity<HttpStatus> addRouteDetail(@PathVariable Long routeId, @PathVariable Long deliveryOrderDetailId, @PathVariable int qty) {
        routeDetailService.addRouteDetail(routeId, deliveryOrderDetailId, qty);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/routes/route-points/{id}")
    public List<RouteDeliveryPointDTO> getRouteDeliveryPointDTOList(@PathVariable Long id) {
        return routeDeliveryPointService.getRouteDeliveryPointDTOList(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/routes/route-points")
    public ResponseEntity<HttpStatus> getRouteDeliveryPointDTOList(@RequestBody RouteDeliveryPointDTO deliveryPointDTO) {
        routeDeliveryPointService.createRouteDeliveryPoint(deliveryPointDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}

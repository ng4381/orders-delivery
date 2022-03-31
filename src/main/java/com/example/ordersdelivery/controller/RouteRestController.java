package com.example.ordersdelivery.controller;

import com.example.ordersdelivery.entity.Route;
import com.example.ordersdelivery.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RouteRestController {
    private final RouteService routeService;

    @GetMapping("/routes")
    public List<Route> getRoutesList() {
        return routeService.getRoutesList();
    }
}

package com.example.ordersdelivery.service;

import com.example.ordersdelivery.entity.Route;
import com.example.ordersdelivery.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public List<Route> getRoutesList() {
        return routeRepository.findAll();
    }
}
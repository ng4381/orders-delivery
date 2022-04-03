package com.example.ordersdelivery.service;

import com.example.ordersdelivery.entity.Route;
import com.example.ordersdelivery.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final TransportService transportService;

    public List<Route> getRoutesList() {
        return routeRepository.findAll();
    }

    public Route createRoute() {
        Route route = new Route();
        route.setTransport(transportService.getDefaultTransport());
        return routeRepository.save(route);
    }

    public Route getRouteById(Long id) {
        Optional<Route> optionalRoute = routeRepository.findById(id);
        if (optionalRoute.isEmpty()) {
            return null;
        }
        return optionalRoute.get();
    }
}
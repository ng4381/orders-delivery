package com.example.ordersdelivery.service;

import com.example.ordersdelivery.dto.RouteDescDTO;
import com.example.ordersdelivery.entity.Route;
import com.example.ordersdelivery.entity.Transport;
import com.example.ordersdelivery.exception.RouteDetailsIsNotEmptyException;
import com.example.ordersdelivery.exception.RouteNotFoundException;
import com.example.ordersdelivery.mapper.RouteDescMapper;
import com.example.ordersdelivery.repository.RouteDeliveryPointRepository;
import com.example.ordersdelivery.repository.RouteDetailRepository;
import com.example.ordersdelivery.repository.RouteRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final RouteDetailRepository routeDetailRepository;
    private final RouteDeliveryPointRepository routeDeliveryPointRepository;
    private final TransportService transportService;
    private final RouteDescMapper routeDescMapper;

    public List<Route> getRoutesList() {
        return routeRepository.findAll();
    }

    public Route createRoute() {
        Route route = new Route();
        route.setTransport(transportService.getDefaultTransport());
        return routeRepository.save(route);
    }

    public RouteDescDTO getRouteDesc(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route with id " + id + " was not found!"));

        return routeDescMapper.mapToDto(route);
    }

    public Route getRouteById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route not found id: " + id));
    }

    public void updateTransport(Long id, Long routeId) {
        log.info("updating transport in route " + routeId + " transportId " + id);
        Route route = getRouteById(routeId);
        Transport transport = transportService.getTransportById(id);
        route.setTransport(transport);

        routeRepository.save(route);
    }

    public void deleteRoute(Long routeId) {


        if (!routeDetailRepository.getRouteDetailByRouteId(routeId).isEmpty()) {
            log.info("EXCEPTION!!!!! Route id=" + routeId + " details is not empty");
            throw new RouteDetailsIsNotEmptyException("Route id=" + routeId + " details is not empty");
        }

        /*
        if(!routeDeliveryPointRepository.getRouteDeliveryPointByRouteId(routeId).isEmpty()) {
            throw new RouteDeliveryPointsIsNotEmptyException("Route id=" + routeId + " delivery points is not empty");
        }

         */

        routeRepository.deleteById(routeId);
        log.info("Route id=" + routeId + " deleted");
    }
}
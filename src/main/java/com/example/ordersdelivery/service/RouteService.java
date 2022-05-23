package com.example.ordersdelivery.service;

import com.example.ordersdelivery.dto.RouteDescDTO;
import com.example.ordersdelivery.entity.Route;
import com.example.ordersdelivery.entity.Transport;
import com.example.ordersdelivery.exception.RouteDeliveryPointsIsNotEmptyException;
import com.example.ordersdelivery.exception.RouteDetailsIsNotEmptyException;
import com.example.ordersdelivery.exception.RouteNotFoundException;
import com.example.ordersdelivery.repository.RouteDeliveryPointRepository;
import com.example.ordersdelivery.repository.RouteDetailRepository;
import com.example.ordersdelivery.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final RouteDetailRepository routeDetailRepository;
    private final RouteDeliveryPointRepository routeDeliveryPointRepository;
    private final TransportService transportService;

    public List<Route> getRoutesList() {
        return routeRepository.findAll();
    }

    public Route createRoute() {
        Route route = new Route();
        route.setTransport(transportService.getDefaultTransport());
        return routeRepository.save(route);
    }

    public RouteDescDTO getRouteDesc(Long id){
        RouteDescDTO routeDescDTO = new RouteDescDTO();
        Optional<Route> optionalRoute = routeRepository.findById(id);
        if(optionalRoute.isEmpty()) {
            throw new RouteNotFoundException("Route with id " + id + " was not found!");
        }

        Route route = optionalRoute.get();
        routeDescDTO.setRouteId(route.getId());
        routeDescDTO.setTransportId(route.getTransport().getId());
        routeDescDTO.setTransportType(route.getTransport().getType());
        routeDescDTO.setFrom(route.getFrom());
        routeDescDTO.setETA(route.getETA());
        routeDescDTO.setETD(route.getETD());

        return routeDescDTO;
    }

    public Route getRouteById(Long id) {
        Optional<Route> optionalRoute = routeRepository.findById(id);
        if (optionalRoute.isEmpty()) {
            return null;
        }
        return optionalRoute.get();
    }

    public void updateTransport(Long id, Long routeId) {
        log.info("updating transport in route " + routeId + " transportId " + id);
        Route route = getRouteById(routeId);
        Transport transport = transportService.getTransportById(id);
        route.setTransport(transport);

        routeRepository.save(route);
    }

    public void deleteRoute(Long routeId) {


        if(!routeDetailRepository.getRouteDetailByRouteId(routeId).isEmpty()) {
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
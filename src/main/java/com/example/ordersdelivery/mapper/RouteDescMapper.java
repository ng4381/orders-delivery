package com.example.ordersdelivery.mapper;

import com.example.ordersdelivery.dto.RouteDescDTO;
import com.example.ordersdelivery.entity.Route;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RouteDescMapper {

    public RouteDescDTO mapToDto(Route route) {
        RouteDescDTO routeDescDTO = new RouteDescDTO();
        routeDescDTO.setRouteId(route.getId());
        routeDescDTO.setTransportId(route.getTransport().getId());
        routeDescDTO.setTransportType(route.getTransport().getType());
        routeDescDTO.setFrom(route.getFrom());
        routeDescDTO.setETA(route.getETA());
        routeDescDTO.setETD(route.getETD());

        return routeDescDTO;
    }
}

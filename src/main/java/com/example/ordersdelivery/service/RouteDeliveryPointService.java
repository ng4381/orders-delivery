package com.example.ordersdelivery.service;

import com.example.ordersdelivery.dto.RouteDeliveryPointDTO;
import com.example.ordersdelivery.entity.RouteDeliveryPoint;
import com.example.ordersdelivery.repository.RouteDeliveryPointRepository;
import com.example.ordersdelivery.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteDeliveryPointService {

    private final RouteDeliveryPointRepository routeDeliveryPointRepository;
    private final RouteService routeService;

    public List<RouteDeliveryPoint> getRouteDeliveryPointByRouteId(Long id) {
        return routeDeliveryPointRepository.getRouteDeliveryPointByRouteId(id);
    }

    public List<RouteDeliveryPointDTO> getRouteDeliveryPointDTOList(Long id) {
        return routeDeliveryPointRepository.getRouteDeliveryPointByRouteId(id).stream()
                .map(routeDeliveryPoint -> new RouteDeliveryPointDTO(routeDeliveryPoint))
                .collect(Collectors.toList());
    }


    public void createRouteDeliveryPoint(RouteDeliveryPointDTO deliveryPointDTO) {
        RouteDeliveryPoint routeDeliveryPoint = new RouteDeliveryPoint();
        routeDeliveryPoint.setRoute(routeService.getRouteById(deliveryPointDTO.getRoute_id()));
        routeDeliveryPoint.setDelivery_to(deliveryPointDTO.getDelivery_to());
        routeDeliveryPointRepository.save(routeDeliveryPoint);
    }


    public void deleteRouteDeliveryPoint(Long id) {
        routeDeliveryPointRepository.deleteById(id);
    }
}

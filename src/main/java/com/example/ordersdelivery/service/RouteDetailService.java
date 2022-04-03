package com.example.ordersdelivery.service;

import com.example.ordersdelivery.dto.RouteDTO;
import com.example.ordersdelivery.dto.RouteDetailsDTO;
import com.example.ordersdelivery.entity.DeliveryOrderDetail;
import com.example.ordersdelivery.entity.Route;
import com.example.ordersdelivery.entity.RouteDetail;
import com.example.ordersdelivery.repository.RouteDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RouteDetailService {
    private final RouteService routeService;
    private final RouteDetailRepository routeDetailRepository;
    private final DeliveryOrderDetailService deliveryOrderDetailService;

    public List<RouteDTO> getAllRouteDetailsGroupedByRoutes() {

        List<RouteDTO> routeDTOList = new ArrayList<>();

        routeDetailRepository.getAllRoutesDetails().stream()
                .collect(Collectors.groupingBy(RouteDetailsDTO::getRouteId))
                .forEach((aLong, routeDetailsDTOS) -> {
                    RouteDTO routeDTO = new RouteDTO();
                    routeDTO.setRouteId(aLong);
                    routeDTO.setRouteDetails(routeDetailsDTOS);
                    routeDTOList.add(routeDTO);
                });

        return routeDTOList;
    }

    public void addRouteDetail(Long routeId, Long deliveryOrderDetailId, int qty) {
            RouteDetail routeDetail = routeDetailRepository.getByRouterIdAndDeliveryOrderDetailId(routeId, deliveryOrderDetailId);
            DeliveryOrderDetail deliveryOrderDetail = deliveryOrderDetailService.getDeliveryOrderDetailById(deliveryOrderDetailId);
            Route route = routeService.getRouteById(routeId);

            if(routeDetail==null){
                log.info("Creating new RouteDetail");
                RouteDetail newRouteDetail = new RouteDetail();
                newRouteDetail.setRoute(route);
                newRouteDetail.setDeliveryOrderDetail(deliveryOrderDetail);
                newRouteDetail.setQty(qty);
                createRouteDetail(newRouteDetail);
            }else {
                log.info("Change qty in RouteDetail current qty = " + routeDetail.getQty());
                routeDetail.setQty(routeDetail.getQty() + qty);
                updateRouteDetail(routeDetail);
            }
    }

    public RouteDetail getByRouterIdAndDeliveryOrderDetailId(Long routeId, Long deliveryOrderId) {
        return routeDetailRepository.getByRouterIdAndDeliveryOrderDetailId(routeId, deliveryOrderId);
    }

    public RouteDetail createRouteDetail(RouteDetail routeDetail) {
        return routeDetailRepository.save(routeDetail);
    }

    public RouteDetail updateRouteDetail(RouteDetail routeDetail) {
        return routeDetailRepository.save(routeDetail);
    }
}

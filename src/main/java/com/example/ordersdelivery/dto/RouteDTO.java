package com.example.ordersdelivery.dto;

import com.example.ordersdelivery.entity.RouteDeliveryPoint;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RouteDTO {
    private Long routeId;
    private List<RouteDetailsDTOImpl> routeDetails;
    private List<RouteDeliveryPointDTO> routeDeliveryPoints;

    public void setRouteDeliveryPoints(List<RouteDeliveryPoint> routeDeliveryPointsList) {
        this.routeDeliveryPoints = routeDeliveryPointsList.stream()
                .map(routeDeliveryPointDTO -> new RouteDeliveryPointDTO(routeDeliveryPointDTO))
                .collect(Collectors.toList());;
    }
}

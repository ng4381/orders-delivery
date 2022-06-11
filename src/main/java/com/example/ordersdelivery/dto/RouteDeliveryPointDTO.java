package com.example.ordersdelivery.dto;

import com.example.ordersdelivery.entity.RouteDeliveryPoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteDeliveryPointDTO {
    private Long id;
    private Long route_id;
    private String delivery_to;

    public RouteDeliveryPointDTO(RouteDeliveryPoint routeDeliveryPoint) {
        this.id = routeDeliveryPoint.getId();
        this.route_id = routeDeliveryPoint.getRoute().getId();
        this.delivery_to = routeDeliveryPoint.getDelivery_to();
    }
}

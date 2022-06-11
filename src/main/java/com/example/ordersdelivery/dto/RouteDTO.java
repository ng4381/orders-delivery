package com.example.ordersdelivery.dto;

import com.example.ordersdelivery.entity.RouteDeliveryPoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDTO {
    private Long routeId;
    private Long transportId;
    private String transportType;
    private double transportVolume;
    private double totalLoadVolume;
    private double transportVolumeRemain;
    private List<RouteDetailsDTOImpl> routeDetails;
    private List<RouteDeliveryPointDTO> routeDeliveryPoints;

    public void setRouteDeliveryPoints(List<RouteDeliveryPoint> routeDeliveryPointsList) {
        this.routeDeliveryPoints = routeDeliveryPointsList.stream()
                .map(routeDeliveryPointDTO -> new RouteDeliveryPointDTO(routeDeliveryPointDTO))
                .collect(Collectors.toList());;
    }


    public RouteDTO(RouteDetailsDTOImpl routeDetailsDTO) {
        this.routeId = routeDetailsDTO.getRouteId();
        this.transportId = routeDetailsDTO.getTransportId();
        this.transportType = routeDetailsDTO.getTransportType();
        this.transportVolume = routeDetailsDTO.getTransportVolume();
    }

    public void setTransportVolumeRemain() {
        this.transportVolumeRemain = transportVolume - totalLoadVolume;
    }
}

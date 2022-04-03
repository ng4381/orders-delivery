package com.example.ordersdelivery.dto;

import lombok.Data;

import java.util.List;

@Data
public class RouteDTO {
    private Long routeId;
    private List<RouteDetailsDTO> routeDetails;
}

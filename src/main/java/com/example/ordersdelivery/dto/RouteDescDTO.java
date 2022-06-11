package com.example.ordersdelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDescDTO {
    private Long routeId;
    private Long transportId;
    private String transportType;
    private LocalDate ETA;
    private LocalDate ETD;
    private String from;
}

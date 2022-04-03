package com.example.ordersdelivery.dto;

public interface RouteDetailsDTO {
    Long getRouteId();
    Long getRouteDetailId();
    Long getDeliveryOrderDetailId();
    String getProductName();
    int getQty();
}

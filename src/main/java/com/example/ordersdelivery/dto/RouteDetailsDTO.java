package com.example.ordersdelivery.dto;

public interface RouteDetailsDTO {
    Long getRouteId();
    Long getDeliveryOrderDetailId();
    String getProductName();
    int getQty();
}

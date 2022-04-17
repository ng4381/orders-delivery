package com.example.ordersdelivery.dto;

public interface RouteDetailsDTO {
    Long getRouteId();
    Long getRouteDetailId();
    Long getDeliveryOrderDetailId();
    Long getProductId();
    Long getDeliveryOrderId();
    Long getDeliveryOrderExtOrderId();
    String getProductName();
    int getQty();
}

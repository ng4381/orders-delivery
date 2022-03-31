package com.example.ordersdelivery.dto;

public interface RouteDetailsDTO {
    Long getId();
    Long getDeliveryOrderId();
    String getDeliveryOrderExtOrderId();
    String getProductName();
    int getQty();
}

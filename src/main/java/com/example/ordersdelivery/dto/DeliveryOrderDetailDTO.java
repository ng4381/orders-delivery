package com.example.ordersdelivery.dto;

public interface DeliveryOrderDetailDTO {
    Long getId();
    Long getDeliveryOrderId();
    String getDeliveryOrderExtOrderId();
    String getProductName();
    int getQty();

}


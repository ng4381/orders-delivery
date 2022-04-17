package com.example.ordersdelivery.dto;

public interface DeliveryOrderDetailDTO {
    Long getId();
    Long getDeliveryOrderId();
    String getDeliveryOrderExtOrderId();
    Long getProductId();
    String getProductName();
    int getTotal();
    int getQty();

}


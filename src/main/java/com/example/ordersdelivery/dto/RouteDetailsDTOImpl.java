package com.example.ordersdelivery.dto;

import lombok.Setter;

@Setter
public class RouteDetailsDTOImpl implements RouteDetailsDTO{
    private Long routeId;
    private Long routeDetailId;
    private Long deliveryOrderDetailId;
    private Long productId;

    private Long deliveryOrderId;
    private Long deliveryOrderExtOrderId;

    private String productName;
    private int qty;

    public RouteDetailsDTOImpl(RouteDetailsDTO routeDetailsDTO) {
        this.routeId = routeDetailsDTO.getRouteId();
        this.routeDetailId = routeDetailsDTO.getRouteDetailId();
        this.deliveryOrderDetailId = routeDetailsDTO.getDeliveryOrderDetailId();
        this.productId = routeDetailsDTO.getProductId();

        this.deliveryOrderId = routeDetailsDTO.getDeliveryOrderId();
        this.deliveryOrderExtOrderId = routeDetailsDTO.getDeliveryOrderExtOrderId();

        this.productName = routeDetailsDTO.getProductName();
        this.qty = routeDetailsDTO.getQty();
    }

    @Override
    public Long getDeliveryOrderId() {
        return deliveryOrderId;
    }

    @Override
    public Long getDeliveryOrderExtOrderId() {
        return deliveryOrderExtOrderId;
    }

    @Override
    public Long getRouteId() {
        return routeId;
    }

    @Override
    public Long getRouteDetailId() {
        return routeDetailId;
    }

    @Override
    public Long getDeliveryOrderDetailId() {
        return deliveryOrderDetailId;
    }

    @Override
    public Long getProductId() {
        return productId;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public int getQty() {
        return qty;
    }

    @Override
    public Long getTransportId() {
        return null;
    }

    @Override
    public String getTransportType() {
        return null;
    }

    @Override
    public Long getTransportVolume() {
        return null;
    }
}

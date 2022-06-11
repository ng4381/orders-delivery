package com.example.ordersdelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteDetailsDTOImpl implements RouteDetailsDTO{
    private Long routeId;
    private Long routeDetailId;
    private Long deliveryOrderDetailId;
    private Long productId;

    private Long deliveryOrderId;
    private Long deliveryOrderExtOrderId;

    private String productName;
    private double productVolume;
    private int qty;

    private Long transportId;
    private String transportType;
    private double transportVolume;


    public RouteDetailsDTOImpl(RouteDetailsDTO routeDetailsDTO) {
        this.routeId = routeDetailsDTO.getRouteId();
        this.routeDetailId = routeDetailsDTO.getRouteDetailId();
        this.deliveryOrderDetailId = routeDetailsDTO.getDeliveryOrderDetailId();
        this.productId = routeDetailsDTO.getProductId();

        this.deliveryOrderId = routeDetailsDTO.getDeliveryOrderId();
        this.deliveryOrderExtOrderId = routeDetailsDTO.getDeliveryOrderExtOrderId();

        this.productName = routeDetailsDTO.getProductName();
        this.qty = routeDetailsDTO.getQty();

        this.transportId = routeDetailsDTO.getTransportId();
        this.transportType = routeDetailsDTO.getTransportType();
        this.transportVolume = routeDetailsDTO.getTransportVolume();
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
        return transportId;
    }

    @Override
    public String getTransportType() {
        return transportType;
    }

    @Override
    public double getTransportVolume() {
        return transportVolume;
    }

    public double getProductVolume() {
        return productVolume;
    }
}

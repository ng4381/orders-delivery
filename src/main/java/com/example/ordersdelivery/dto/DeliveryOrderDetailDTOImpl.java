package com.example.ordersdelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderDetailDTOImpl implements DeliveryOrderDetailDTO{

    private Long id;
    private Long deliveryOrderId;
    private String deliveryOrderExtOrderId;
    private Long productId;
    private String productName;
    private int total;
    private int qty;

    public DeliveryOrderDetailDTOImpl(DeliveryOrderDetailDTO deliveryOrderDetailDTO) {
        this.id = deliveryOrderDetailDTO.getId();
        this.deliveryOrderId = deliveryOrderDetailDTO.getDeliveryOrderId();
        this.deliveryOrderExtOrderId = deliveryOrderDetailDTO.getDeliveryOrderExtOrderId();
        this.productId = deliveryOrderDetailDTO.getProductId();
        this.productName = deliveryOrderDetailDTO.getProductName();
        this.total = deliveryOrderDetailDTO.getTotal();
        this.qty = deliveryOrderDetailDTO.getQty();
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getDeliveryOrderId() {
        return deliveryOrderId;
    }

    @Override
    public String getDeliveryOrderExtOrderId() {
        return deliveryOrderExtOrderId;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public Long getProductId() {
        return productId;
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public int getQty() {
        return qty;
    }
}

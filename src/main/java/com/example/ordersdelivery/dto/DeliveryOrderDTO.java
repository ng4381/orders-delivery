package com.example.ordersdelivery.dto;

import com.example.ordersdelivery.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DeliveryOrderDTO {
    private int extOrderId;
    private List<DeliveryOrderDetailDTO> deliveryOrderDetails;

    public DeliveryOrderDTO() {
    }

    @Getter @Setter
    public static class DeliveryOrderDetailDTO {
        private Product product;
        //private Long id;
        //private String name;
        private int qty;

        public DeliveryOrderDetailDTO() {
        }
    }

    @Override
    public String toString() {

        String dod_str = "";
        for(DeliveryOrderDetailDTO deliveryOrderDetailDTO : deliveryOrderDetails) {
            dod_str += deliveryOrderDetailDTO.getProduct().getName() + "  " + deliveryOrderDetailDTO.getQty() + "; ";
        }

        return "DeliveryOrderDTO{" +
                "extOrderId=" + extOrderId +
                ", deliveryOrderDetails=" + dod_str +
                '}';
    }
}



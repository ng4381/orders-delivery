package com.example.ordersdelivery.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "deliveryorderdetail")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class DeliveryOrderDetail {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "do_id")
    private DeliveryOrder deliveryOrder;

    /*
    @ManyToOne
    @JoinColumn(name = "product_id")
     */
    private Long product_id;

    private int qty;

    public DeliveryOrderDetail() {
    }
}

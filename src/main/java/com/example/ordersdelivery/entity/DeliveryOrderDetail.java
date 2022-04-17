package com.example.ordersdelivery.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "deliveryorderdetail")
@Getter
@Setter
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

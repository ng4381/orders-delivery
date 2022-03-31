package com.example.ordersdelivery.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "routedetail")
@Getter
@Setter
public class RouteDetail {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "deliveryorderdetail_id")
    private DeliveryOrderDetail deliveryOrderDetail;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private int qty;
}

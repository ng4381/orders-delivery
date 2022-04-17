package com.example.ordersdelivery.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "routedeliverypoint")
@Getter
@Setter
public class RouteDeliveryPoint {
    @Id
    @GeneratedValue
    private Long id;
    private String delivery_to;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
}

package com.example.ordersdelivery.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "deliveryorder")
@Getter
@Setter
public class DeliveryOrder {
    @Id
    @GeneratedValue
    private Long id;

    private int extOrderId;

    private LocalDate ETA;
    private LocalDate ETD;

    public DeliveryOrder() {
    }
}

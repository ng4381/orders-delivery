package com.example.ordersdelivery.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "route")
@Getter
@Setter
public class Route {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

    private LocalDate ETA;
    private LocalDate ETD;

    @Column(name = "delivery_from")
    private String from;
    @Column(name = "delivery_to")
    private String to;
}

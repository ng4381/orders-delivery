package com.example.ordersdelivery.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transport")
@Getter
@Setter
public class Transport {
    @Id
    @GeneratedValue
    private Long id;

    private String number;
    private String type;
}

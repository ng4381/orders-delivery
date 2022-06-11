package com.example.ordersdelivery.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "transport")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transport {
    @Id
    @GeneratedValue
    private Long id;

    private String number;
    private String type;

    @Column(name = "H")
    private Long H;
    @Column(name = "W")
    private Long W;
    @Column(name = "L")
    private Long L;
    @Column(name = "volume")
    private Double volume;
}

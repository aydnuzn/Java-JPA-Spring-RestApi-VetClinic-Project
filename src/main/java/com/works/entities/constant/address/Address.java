package com.works.entities.constant.address;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Integer address_id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cid")
    City city;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "did")
    District district;

    private String cu_address;
}

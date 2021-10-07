package com.works.entities;

import com.works.entities.listener.BaseEntity;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Sale extends BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sale_id;

    private String sale_code;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cu_id")
    Customer customer;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id")
    Product product;

    private Integer sale_number;//miktar
    private Integer sale_total;//Total Ödeme Tutarı

    private Integer sale_type;
    private String sale_detail;

}
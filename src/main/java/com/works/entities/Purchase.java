package com.works.entities;

import com.works.entities.listener.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Purchase extends BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer purchase_id;

    private String purchase_code;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "supplier_id")
    Supplier supplier;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id")
    Product product;

    private Integer purchase_number;//miktar
    private Integer purchase_total;//Total Ödeme Tutarı

    private String purchase_detail;

    private Integer purchase_type;



}
package com.works.entities;

import com.works.entities.listener.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PaymentIn extends BaseEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pin_id;
    private String pin_detail;
    private Integer pin_price;
    private Integer pin_operationType;
    private Integer pin_paymentType;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cu_id")
    Customer customer;


}

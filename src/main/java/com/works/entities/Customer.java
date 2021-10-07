package com.works.entities;

import com.works.entities.constant.address.Address;
import com.works.entities.listener.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer extends BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cu_id;

    private String cu_name;
    private String cu_surname;
    private String cu_tel1;
    private String cu_tel2;
    @Column(unique = true)
    private String cu_mail;
    private String cu_taxname;
    private String cu_note;
    @Column(unique = true)
    private String cu_tcnumber;
    private String cu_rateOfDiscount;
    private String cu_smsNotice;
    private String cu_mailNotice;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "address_id")
    Address address;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cu_gr_id")
    CustomerGroup customerGroup;

}

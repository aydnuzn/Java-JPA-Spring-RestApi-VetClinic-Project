package com.works.entities.constant.address;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer did;

    @Column(length = 50)
    private String dname;

    private Integer cid;
}

package com.works.entities.constant.pets;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class TypePet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ty_id;

    private String ty_name;

}

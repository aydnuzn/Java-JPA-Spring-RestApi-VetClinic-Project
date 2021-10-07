package com.works.entities.constant.pets;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ColorPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer color_id;
    private String color_name;

}

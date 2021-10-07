package com.works.entities.constant.pets;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class BreedPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer br_id;

    private String br_name;

    private Integer type_pet_id;
}

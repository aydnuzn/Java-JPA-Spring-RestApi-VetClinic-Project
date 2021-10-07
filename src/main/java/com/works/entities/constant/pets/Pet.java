package com.works.entities.constant.pets;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pet_id;

    private String pet_name;
    private String pet_chipNumber;//çip numarası
    private String pet_earTag;//kulak küpesi
    private Date pet_bornDate;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "join_type_breed_pet")
    private JoinTypeBreedPet joinTypeBreedPet;

    private Boolean pet_neutering;//kısırlaştırma

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "color_pet_color_id")
    private ColorPet colorPet;

    private Boolean pet_gender;
}

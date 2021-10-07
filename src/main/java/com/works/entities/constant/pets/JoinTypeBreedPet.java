package com.works.entities.constant.pets;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class JoinTypeBreedPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jtbp_id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "ty_id")
    TypePet typePet;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "br_id")
    BreedPet breedPet;

}

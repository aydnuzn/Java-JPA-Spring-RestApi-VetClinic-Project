package com.works.repositories._jpa;

import com.works.entities.constant.pets.TypePet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePetRepository extends JpaRepository<TypePet, Integer> {

}

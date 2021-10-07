package com.works.repositories._jpa;

import com.works.entities.constant.pets.ColorPet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorPetRepository extends JpaRepository<ColorPet, Integer> {
}

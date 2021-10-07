package com.works.repositories._jpa;

import com.works.entities.constant.pets.JoinPetCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface JoinPetCustomerRepository extends JpaRepository<JoinPetCustomer, Integer> {
    @Query(value = "Select pet_id From join_pet_customer WHERE pet_id = ?1", nativeQuery = true)
    Optional<JoinPetCustomer> deleteJoinPet(Integer pet_id);

}

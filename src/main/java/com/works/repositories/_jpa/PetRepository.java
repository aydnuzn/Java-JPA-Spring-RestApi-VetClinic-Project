package com.works.repositories._jpa;

import com.works.entities.constant.pets.Pet;
import com.works.entities.projections.AllCustomerPetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    @Query(value = "SELECT p.pet_id as petId,\n" +
            "CONCAT( c.cu_name, \" \", c.cu_surname ) AS cuname,c.cu_mail AS cumail,\n" +
            "p.pet_born_date AS born,p.pet_chip_number AS chipnumber,p.pet_gender AS gender,\n" +
            "pet_name AS pname, p.pet_neutering AS neutering, tp.ty_name AS typename,\n" +
            "bt.br_name AS breedname\n" +
            "FROM customer AS c\n" +
            "INNER JOIN join_pet_customer AS j ON j.cu_id = c.cu_id\n" +
            "INNER JOIN pet AS p ON p.pet_id = j.pet_id\n" +
            "INNER JOIN join_type_breed_pet AS jtbp ON jtbp.jtbp_id = p.join_type_breed_pet\n" +
            "INNER JOIN breed_pet AS bt ON jtbp.br_id = bt.br_id\n" +
            "INNER JOIN type_pet AS tp ON tp.ty_id = jtbp.ty_id\n" +
            "WHERE c.cu_id = ?1", nativeQuery = true)
    public List<AllCustomerPetInfo> getCustomerPets(Integer id);
}

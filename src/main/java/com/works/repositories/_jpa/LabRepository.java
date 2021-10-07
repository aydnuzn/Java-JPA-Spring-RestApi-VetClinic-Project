package com.works.repositories._jpa;

import com.works.entities.Lab;
import com.works.entities.projections.LabInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab, Integer> {
    @Query(value = "SELECT\n" +
            "      lab_id,\n" +
            "            lab_detail,\n" +
            "              lab_type,\n" +
            "                CONCAT( cu_name, ' ', cu_surname, ' - ', pet_name ) AS customer,\n" +
            "             CONCAT( us_name, ' ', us_surname ) AS doctor,\n" +
            "                  lab_file\n" +
            "                FROM\n" +
            "                  lab\n" +
            "                  INNER JOIN join_pet_customer on join_pet_customer.jpt_id = lab.jpt_id\n" +
            "                inner JOIN pet on pet.pet_id = join_pet_customer.pet_id\n" +
            "                inner join customer on customer.cu_id = join_pet_customer.cu_id\n" +
            "                  INNER JOIN `user` ON `user`.us_id = lab.us_id", nativeQuery = true)
    List<LabInfo> labPageRowsData();

    @Query(value = "SELECT\n" +
            "           lab_id,\n" +
            "             lab_detail,\n" +
            "                 lab_type,\n" +
            "      CONCAT( cu_name, ' ', cu_surname, ' - ', pet_name ) AS customer,\n" +
            "                 CONCAT( us_name, ' ', us_surname ) AS doctor,\n" +
            "                       lab_file\n" +
            "                      FROM\n" +
            "                       lab\n" +
            "                      INNER JOIN join_pet_customer on join_pet_customer.jpt_id = lab.jpt_id\n" +
            "                   inner JOIN pet on pet.pet_id = join_pet_customer.pet_id\n" +
            "                       inner join customer on customer.cu_id = join_pet_customer.cu_id\n" +
            "                     INNER JOIN `user` ON `user`.us_id = lab.us_id\n" +
            "                     WHERE\n" +
            "                  lab_id LIKE CONCAT( '%', ?1, '%' )\n" +
            "                     OR lab_detail LIKE CONCAT( '%', ?1, '%')\n" +
            "                    OR lab_type LIKE CONCAT( '%', ?1, '%' ) \n" +
            "           OR CONCAT( cu_name, ' ', cu_surname, ' - ', pet_name ) LIKE CONCAT( '%', ?1, '%' ) \n" +
            "                    OR CONCAT( us_name, ' ', us_surname ) LIKE CONCAT( '%', ?1, '%' ) \n" +
            "                   OR lab_file LIKE CONCAT( '%', ?1, '%' );", nativeQuery = true)
    List<LabInfo> labPageRowsDataSearch(String search);

    @Query(value = " select count(*) from lab where jpt_id = ?1", nativeQuery = true)
    Integer isLabResult(Integer jpt_id);

}

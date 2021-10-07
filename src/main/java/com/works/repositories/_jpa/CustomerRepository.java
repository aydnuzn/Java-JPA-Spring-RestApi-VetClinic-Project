package com.works.repositories._jpa;

import com.works.entities.Customer;
import com.works.entities.projections.CustomerInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select jpc.jpt_id,concat(cu_name, ' ', cu_surname) as cu_name, pet_name from customer as c\n" +
            "inner JOIN join_pet_customer as jpc on c.cu_id = jpc.cu_id\n" +
            "INNER JOIN pet as p on p.pet_id = jpc.pet_id", nativeQuery = true)
    List<CustomerInfo> allCustomerAndPetInfos();

    @Query(value = "select * from Customer where upper(cu_name) like upper(concat('%', ?1, '%'))", nativeQuery = true)
    List<Customer> searchCustomer(String cu_name);

    @Query(value = "select * from customer order by created_date desc limit 0,30", nativeQuery = true)
    List<Customer> getCustomerSonOtuz();

    @Query(value = "select c from Customer c order by c.cu_id")
    List<Customer> findByOrderByCu_idAsc(Pageable pageable);

    @Query(value = "select customer.address_id from customer inner join address on address.address_id = customer.address_id where customer.cu_id = ?1", nativeQuery = true)
    Integer findByAddressId(Integer cu_id);
}
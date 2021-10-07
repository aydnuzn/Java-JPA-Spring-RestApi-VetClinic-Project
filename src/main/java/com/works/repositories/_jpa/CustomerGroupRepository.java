package com.works.repositories._jpa;

import com.works.entities.CustomerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Integer> {
    @Query("select c from CustomerGroup c where upper(c.cu_gr_name) like upper(concat('%', ?1, '%'))")
    List<CustomerGroup> findByCu_gr_nameContainsAllIgnoreCase(String cu_gr_name);
}

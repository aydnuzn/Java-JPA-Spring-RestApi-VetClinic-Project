package com.works.repositories._jpa;

import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where upper(p.product_name) like upper(concat('%', ?1, '%'))")
    List<Product> findByProduct_nameContainsAllIgnoreCase(String product_name);

}

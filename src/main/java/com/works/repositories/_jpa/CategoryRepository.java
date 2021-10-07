package com.works.repositories._jpa;

import com.works.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where upper(c.category_title) like upper(concat('%', ?1, '%'))")
    List<Category> findByCategory_titleContainsIgnoreCase(String category_title);

}

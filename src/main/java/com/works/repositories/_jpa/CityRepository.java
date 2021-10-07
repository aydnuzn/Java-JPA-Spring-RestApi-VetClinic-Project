package com.works.repositories._jpa;

import com.works.entities.constant.address.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {

}

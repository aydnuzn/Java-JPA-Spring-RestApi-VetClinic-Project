package com.works.repositories._jpa;

import com.works.entities.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Logger, Integer> {
}

package com.works.repositories._redis;

import com.works.models.redis.CitySession;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableRedisRepositories
public interface CitySessionRepository extends CrudRepository<CitySession, String> {
}

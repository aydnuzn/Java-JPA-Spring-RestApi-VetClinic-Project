package com.works.repositories._redis;

import com.works.entities.constant.address.District;
import com.works.models.redis.DistrictSession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableRedisRepositories
public interface DistrictSessionRepository extends CrudRepository<DistrictSession, String> {

    List<DistrictSession> findByCityid(String cityid);
}

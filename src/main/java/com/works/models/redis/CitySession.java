package com.works.models.redis;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Data
@RedisHash("sessioncity")
public class CitySession {

    @Id
    private String id;
    private String cityname;

}

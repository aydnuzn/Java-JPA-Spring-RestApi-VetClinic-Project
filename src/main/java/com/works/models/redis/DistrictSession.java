package com.works.models.redis;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@Data
@RedisHash("sessiondistrict")
public class DistrictSession {
    @Id
    private String id;
    private String districtname;
    @Indexed
    private String cityid;
}

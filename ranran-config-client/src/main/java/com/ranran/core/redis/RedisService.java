package com.ranran.core.redis;

import java.util.List;

public interface RedisService {
    List selectRedis(RedisSearchVo redisSearchVo);
}

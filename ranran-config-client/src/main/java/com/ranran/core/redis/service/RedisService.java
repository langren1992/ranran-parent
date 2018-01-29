package com.ranran.core.redis.service;

import com.ranran.core.redis.vo.RedisDataGirdVo;
import com.ranran.core.redis.vo.RedisDeleteVo;
import com.ranran.core.redis.vo.RedisSelectVo;

import java.util.List;

public interface RedisService {

    /**
     *  查询Redis信息
     * @param redisSelectVo 查询对象
     * @return 查询结果
     */
    List<RedisDataGirdVo> selectRedis(RedisSelectVo redisSelectVo);

    /**
     * 删除Redis信息
     *
     * @param redisDeleteVo 删除对象
     * @return 删除结果
     */
    int deleteRedis(RedisDeleteVo redisDeleteVo);
}

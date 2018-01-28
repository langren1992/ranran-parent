package com.ranran.core.redis.impl;

import com.ranran.core.redis.RanranRedisTemplate;
import com.ranran.core.redis.RedisDataGirdVo;
import com.ranran.core.redis.RedisSearchVo;
import com.ranran.core.redis.RedisService;
import com.ranran.uums.system.redis.DictKeyRedis;
import com.ranran.uums.system.redis.DictValueRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RanranRedisTemplate ranranRedisTemplate;

    @Override
    public List selectRedis(RedisSearchVo redisSearchVo) {
        String patten  = redisSearchVo.getKey();
        Set<String> keys = new HashSet<String>();
        if ("".equalsIgnoreCase(patten) || null == patten){
            keys = ranranRedisTemplate.keys("*");
        }else {
            keys = ranranRedisTemplate.keys("*"+patten+"*");
        }
        RedisDataGirdVo redisDataGirdVo;
        List<RedisDataGirdVo> redisDataGirdVos = new ArrayList<RedisDataGirdVo>();
        for (String key: keys) {
            redisDataGirdVo = new RedisDataGirdVo();
            redisDataGirdVo.setKey(key);
            redisDataGirdVo.setValue(ranranRedisTemplate.getForString(key));
            redisDataGirdVos.add(redisDataGirdVo);
        }
        return redisDataGirdVos;
    }
}

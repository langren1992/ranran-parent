package com.ranran.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 封装Redis
 *
 * @author 曾睿
 * @create 2017-11-19 19:19
 **/
@Component
public class AbstractRedis<K,V> {


    @Autowired
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

    public String get(String key) throws Exception  {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

}

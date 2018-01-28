package com.ranran.core.redis;

import com.ranran.core.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service
public class RanranRedisTemplate {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * String=String 根据时间有效期
     *
     * @param redisOperate 缓存操作对象
     * @param seconds 自动时效时间(单位：秒 s )
     */
    public void setForString(RedisOperate redisOperate,long seconds){
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        String key = redisOperate.getKey();
        String value = redisOperate.getValue();
        if ("".equalsIgnoreCase(key) || null == key){
            throw new RanRanRedisException(new ErrorCode(1000,"The key connt be null or '' "));
        }
        if ("".equalsIgnoreCase(value) || null == value){
            throw new RanRanRedisException(new ErrorCode(1001,"The value connt be null or '' "));
        }
        stringValueOperations.set(key, value,seconds, TimeUnit.SECONDS);
    }

    /**
     * String=String 永久有效
     *
     * @param redisOperate 缓存操作对象
     */
    public void setForString(RedisOperate redisOperate){
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        String key = redisOperate.getKey();
        String value = redisOperate.getValue();
        if ("".equalsIgnoreCase(key) || null == key || "null".equalsIgnoreCase(key)){
            throw new RanRanRedisException(new ErrorCode(1000,"The key connt be null or '' "));
        }
        if ("".equalsIgnoreCase(value) || null == value || "null".equalsIgnoreCase(value)){
            throw new RanRanRedisException(new ErrorCode(1001,"The value connt be null or '' "));
        }
        stringValueOperations.set(key, value);
    }

    /**
     * 根据 键 获取 值
     * @param key 缓存-键
     * @return 返回String类型字符串
     */
    public String getForString(String key){
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        return stringValueOperations.get(key);
    }

    /**
     * 是否包含-键 缓存
     * @param key 缓存-键
     * @return 包含结果
     */
    public boolean hasKey(String key){
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 删除-键 缓存
     * @param key 缓存-键
     */
    public void delete(String key){
        stringRedisTemplate.delete(key);
    }

    /**
     *  根据模式查询信息
     * @param pattern 查询模式
     * @return Set 返回模式下的所有值
     */
    public Set keys(String pattern){
        return stringRedisTemplate.keys(pattern);
    }

    /**
     * 返回redis缓存类
     * @return RedisTemplate
     */
    public RedisTemplate getRedisTemplate() {
        return stringRedisTemplate;
    }

}

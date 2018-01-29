package com.ranran.core.redis;

import com.alibaba.fastjson.JSONObject;
import com.ranran.core.ErrorCode;
import com.ranran.core.redis.annotation.RedisKey;
import com.ranran.core.redis.annotation.RedisValue;
import com.ranran.uums.system.redis.DictKeyRedis;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

/**
 *
 * 缓存操作类
 * 每个缓存操作类必须包含@RedisKey和@RedisValue注解
 *
 *
 * @Auther zengrui 2018-1-29 20:53 添加方法备注
 */
@Service
public class RanranRedisTemplate {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * String=String 根据时间有效期
     *
     * @param object 缓存操作对象
     * @param seconds 自动时效时间(单位：秒 s )
     */
    public void setForString(Object object,long seconds) throws IllegalAccessException {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        Field[] fields = object.getClass().getDeclaredFields();
        String key = "";
        String value = "";
        optFieldsKeyValue(object,fields,key,value);
        isErrorKeyValue(key,value);
        stringValueOperations.set(key, JSONObject.toJSONString(value),seconds, TimeUnit.SECONDS);
    }

    /**
     * String=String 根据时间有效期
     *
     * @param object 缓存操作对象
     */
    public void setForString(Object object) throws IllegalAccessException {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        Field[] fields = object.getClass().getDeclaredFields();
        String key = "";
        String value = "";
        optFieldsKeyValue(object,fields,key,value);
        isErrorKeyValue(key,value);
        stringValueOperations.set(key, JSONObject.toJSONString(value));
    }

    /**
     * 判断键值是否异常
     *
     * @param key
     * @param value
     */
    private void isErrorKeyValue(String key, String value) {
        // TODO: 2018/1/28
        // 判断键是否异常
        isErrorKey(key);
        //
        isErrorValue(value);
    }

    /**
     * 判断键是否异常
     * @param key
     */
    private void isErrorKey(String key) {
        // TODO: 2018/1/28
        if(StringUtils.isBlank(key)){
            throw new RanRanRedisException(new ErrorCode(1001,"The key cannot be null or '' "));
        }
    }

    /**
     * 判断值是否异常
     * @param value
     */
    private void isErrorValue(String value) {
        // TODO: 2018/1/28
        if(StringUtils.isBlank(value)){
            throw new RanRanRedisException(new ErrorCode(1001,"The key cannot be null or '' "));
        }
    }



    /**
     *  填写key和value字段的值
     *
     * @param object
     * @param fields
     * @param key
     * @param value
     * @throws IllegalAccessException
     */
    private void optFieldsKeyValue(Object object,Field[] fields,String key,String value) throws IllegalAccessException {
        // TODO: 2018/1/28 添加注释
        for (Field field : fields) {
            // 填写key字段的值
            optFieldKey(object,field,key);
            // 填写value字段的值
            optFieldValue(object,field,value);
        }
    }

    /**
     * 填写key字段的值
     *
     * @param object
     * @param field
     * @param key
     * @throws IllegalAccessException
     */
    private void optFieldKey(Object object,Field field,String key) throws IllegalAccessException {
        // 判断是否是key注解
        if (field.isAnnotationPresent(RedisKey.class)){
            RedisKey redisKey = field.getAnnotation(RedisKey.class);
            // 添加操作权限
            field.setAccessible(true);
            key = (String) field.get(object);
            if (StringUtils.isNotBlank(redisKey.prefix())){
                key = redisKey.prefix()+"."+ key;
            }
            if (StringUtils.isNotBlank(redisKey.suffix())){
                key = key+"."+redisKey.suffix();
            }
            field.set(object,key);
        }
    }

    /**
     * 填写value字段的值
     *
     * @param object
     * @param field
     * @param value
     * @throws IllegalAccessException
     */
    private void optFieldValue(Object object,Field field,String value) throws IllegalAccessException {
        // 判断是否是value注解
        if (field.isAnnotationPresent(RedisValue.class)){
            // 添加操作权限
            field.setAccessible(true);
            value = (String) field.get(object);
        }
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
     * 根据 键 获取 值
     * @param object 缓存-键
     * @return 返回String类型字符串
     */
    public String getForString(Object object) throws IllegalAccessException {
        // 获取操作对象
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        Field[] fields = object.getClass().getDeclaredFields();
        String key = "";
        // 组装key字段值
        optFieldsKey(object,fields,key);
        // 判断key异常
        isErrorKey(key);
        return stringValueOperations.get(key);
    }

    /**
     *
     * @param object
     * @param fields
     * @param key
     * @throws IllegalAccessException
     */
    private void optFieldsKey(Object object,Field[] fields,String key) throws IllegalAccessException {
        // TODO: 2018/1/28 添加注释
        for (Field field : fields) {
            optFieldKey(object,field,key);
        }
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
     * 是否包含-键 缓存
     * @param object 缓存-键
     * @return 包含结果
     */
    public boolean hasKey(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        String key = "";
        optFieldsKey(object,fields,key);
        isErrorKey(key);
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
     * 删除-键 缓存
     * @param object 缓存-键
     */
    public void delete(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        String key = "";
        optFieldsKey(object,fields,key);
        isErrorKey(key);
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

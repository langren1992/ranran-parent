package com.ranran.core.redis.vo;

import java.io.Serializable;

public class RedisDeleteVo implements Serializable{
    
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

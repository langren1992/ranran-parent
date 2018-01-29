package com.ranran.core.redis.vo;

import java.io.Serializable;

public class RedisSelectVo implements Serializable{

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

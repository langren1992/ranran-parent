package com.ranran.core.redis;

import java.io.Serializable;

public class RedisSearchVo implements Serializable{

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

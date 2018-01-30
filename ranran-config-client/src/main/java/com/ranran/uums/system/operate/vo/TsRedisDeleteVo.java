package com.ranran.uums.system.operate.vo;

import java.io.Serializable;

public class TsRedisDeleteVo implements Serializable{
    
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

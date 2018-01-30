package com.ranran.uums.system.operate.vo;

import java.io.Serializable;

public class TsRedisDataGirdVo implements Serializable{

    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

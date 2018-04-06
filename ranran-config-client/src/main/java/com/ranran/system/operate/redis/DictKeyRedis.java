package com.ranran.system.operate.redis;

import com.ranran.core.redis.annotation.RedisKey;
import com.ranran.core.redis.annotation.RedisValue;

import java.util.List;

public class DictKeyRedis{

    @RedisKey(prefix = "com.ranran.uums.system.operate.redis.DictKeyRedis")
    private String tsKey;

    @RedisValue
    private List<DictValueRedis> dictValues;

    public List<DictValueRedis> getDictValues() {
        return dictValues;
    }

    public void setDictValues(List<DictValueRedis> dictValues) {
        this.dictValues = dictValues;
    }

    public String getTsKey() {
        return tsKey;
    }

    public void setTsKey(String tsKey) {
        this.tsKey = tsKey;
    }

}

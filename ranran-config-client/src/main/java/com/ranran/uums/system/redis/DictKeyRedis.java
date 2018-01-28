package com.ranran.uums.system.redis;

import com.alibaba.fastjson.JSONArray;
import com.ranran.core.redis.RedisOperate;
import com.ranran.core.redis.annotation.RedisKey;
import com.ranran.core.redis.annotation.RedisValue;

import java.util.List;

public class DictKeyRedis implements RedisOperate {

    @RedisKey(prefix = "com.ranran.uums.system.redis.DictKeyRedis")
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

    @Override
    public String getKey() {
        return this.getClass().getName()+"."+tsKey;
    }

    @Override
    public String getValue() {
        return JSONArray.toJSONString(dictValues);
    }


}

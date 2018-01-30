package com.ranran.uums.system.operate.service.impl;

import com.ranran.core.redis.RanranRedisTemplate;
import com.ranran.uums.system.operate.vo.TsRedisDataGirdVo;
import com.ranran.uums.system.operate.vo.TsRedisDeleteVo;
import com.ranran.uums.system.operate.vo.TsRedisSelectVo;
import com.ranran.uums.system.operate.service.TsRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TsRedisServiceImpl implements TsRedisService {

    @Autowired
    private RanranRedisTemplate ranranRedisTemplate;

    /**
     *  查询Redis信息
     * @param redisSelectVo 查询对象
     * @return 查询结果
     */
    @Override
    public List selectRedis(TsRedisSelectVo redisSelectVo) {
        String patten  = redisSelectVo.getKey();
        Set<String> keys = new HashSet<String>();
        if ("".equalsIgnoreCase(patten) || null == patten){
            keys = ranranRedisTemplate.keys("*");
        }else {
            keys = ranranRedisTemplate.keys("*"+patten+"*");
        }
        TsRedisDataGirdVo redisDataGirdVo;
        List<TsRedisDataGirdVo> redisDataGirdVos = new ArrayList<TsRedisDataGirdVo>();
        for (String key: keys) {
            redisDataGirdVo = new TsRedisDataGirdVo();
            redisDataGirdVo.setKey(key);
            redisDataGirdVo.setValue(ranranRedisTemplate.getForString(key));
            redisDataGirdVos.add(redisDataGirdVo);
        }
        return redisDataGirdVos;
    }

    /**
     * 删除Redis信息
     *
     * @param redisDeleteVo 删除对象
     * @return 删除结果
     */
    @Override
    public int deleteRedis(TsRedisDeleteVo redisDeleteVo) {
       ranranRedisTemplate.delete(redisDeleteVo.getKey());
       return 1;
    }
}

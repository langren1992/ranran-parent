package com.ranran.system.operate.service;

import com.ranran.system.operate.vo.TsRedisDataGirdVo;
import com.ranran.system.operate.vo.TsRedisSelectVo;
import com.ranran.system.operate.vo.TsRedisDeleteVo;

import java.util.List;

public interface TsRedisService {

    /**
     *  查询Redis信息
     * @param redisSelectVo 查询对象
     * @return 查询结果
     */
    List<TsRedisDataGirdVo> selectRedis(TsRedisSelectVo redisSelectVo);

    /**
     * 删除Redis信息
     *
     * @param redisDeleteVo 删除对象
     * @return 删除结果
     */
    int deleteRedis(TsRedisDeleteVo redisDeleteVo);
}

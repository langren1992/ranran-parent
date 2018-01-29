package com.ranran.core.redis.controller.local;

import com.alibaba.fastjson.JSONObject;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.core.redis.controller.RedisRestController;

import com.ranran.core.redis.vo.RedisDeleteVo;
import com.ranran.core.redis.vo.RedisSelectVo;
import com.ranran.core.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据字典请求处理
 * @Author zengrui 2018-01-25 11:14
 */
@Component
public class RedisRestControllerLocal extends RestBaseController implements RedisRestController {

    @Autowired
    private RedisService redisService;

    /**
     *  查询Redis信息
     * @param request 请求信息
     * @return 统一响应信息
     */
    @Override
    public ResponseResult selectRedis(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/TsDict/selectTsDict.html"));
        RedisSelectVo redisSelectVo = JSONObject.parseObject(reqData,RedisSelectVo.class);
        return this.listResult(redisService.selectRedis(redisSelectVo));
    }

    /**
     * 删除Redis信息
     *
     * @param request 请求信息
     * @return 统一响应信息
     */
    @Override
    public ResponseResult deleteRedis(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode( 102,"/TsDict/selectTsDict.html"));
        RedisDeleteVo redisDeleteVo = JSONObject.parseObject(reqData,RedisDeleteVo.class);
        return this.deleteResult(redisService.deleteRedis(redisDeleteVo));
    }
}

package com.ranran.core.redis.local;

import com.alibaba.fastjson.JSONObject;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.core.redis.RedisRestController;

import com.ranran.core.redis.RedisSearchVo;
import com.ranran.core.redis.RedisService;
import com.ranran.uums.system.operate.vo.TsDictSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据字典请求处理
 * @creator zengrui 2018-01-25 11:14
 */
@Component
public class RedisRestControllerLocal extends RestBaseController implements RedisRestController {

    @Autowired
    private RedisService redisService;

    @Override
    public ResponseResult selectRedis(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/TsDict/selectTsDict.html"));
        RedisSearchVo redisSearchVo = JSONObject.parseObject(reqData,RedisSearchVo.class);
        return this.listResult(redisService.selectRedis(redisSearchVo));
    }
}

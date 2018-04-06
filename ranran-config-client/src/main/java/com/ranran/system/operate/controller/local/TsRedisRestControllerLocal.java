package com.ranran.system.operate.controller.local;

import com.alibaba.fastjson.JSONObject;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;

import com.ranran.system.operate.controller.TsRedisRestController;
import com.ranran.system.operate.service.TsRedisService;
import com.ranran.system.operate.vo.TsRedisSelectVo;
import com.ranran.system.operate.vo.TsRedisDeleteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 缓存请求处理
 * @Author zengrui 2018-01-25 11:14
 */
@Component
public class TsRedisRestControllerLocal extends RestBaseController implements TsRedisRestController {

    @Autowired
    private TsRedisService tsRedisService;

    /**
     *  查询Redis信息
     * @param request 请求信息
     * @return 统一响应信息
     */
    @Override
    public ResponseResult selectRedis(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/TsDict/selectTsDict.html"));
        TsRedisSelectVo redisSelectVo = JSONObject.parseObject(reqData,TsRedisSelectVo.class);
        return this.listResult(tsRedisService.selectRedis(redisSelectVo));
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
        TsRedisDeleteVo redisDeleteVo = JSONObject.parseObject(reqData,TsRedisDeleteVo.class);
        return this.deleteResult(tsRedisService.deleteRedis(redisDeleteVo));
    }
}

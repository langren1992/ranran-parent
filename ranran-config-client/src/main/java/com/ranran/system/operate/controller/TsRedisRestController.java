package com.ranran.system.operate.controller;

import com.ranran.core.ResponseResult;

import javax.servlet.http.HttpServletRequest;

public interface TsRedisRestController {

    /**
     *  查询Redis信息
     * @param request 请求信息
     * @return 统一响应信息
     */
    ResponseResult selectRedis(HttpServletRequest request);

    /**
     * 删除Redis信息
     * @param request 请求信息
     * @return 统一响应信息
     */
    ResponseResult deleteRedis(HttpServletRequest request);
}

package com.ranran.core.redis;

import com.ranran.core.ResponseResult;

import javax.servlet.http.HttpServletRequest;

public interface RedisRestController {

    public ResponseResult selectRedis(HttpServletRequest request);
}

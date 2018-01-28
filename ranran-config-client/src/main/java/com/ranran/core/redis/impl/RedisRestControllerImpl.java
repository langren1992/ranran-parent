package com.ranran.core.redis.impl;

import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.core.redis.RedisRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据字典请求控制类
 * @creator zengrui 2018-01-25 11:14
 */
@RestController(value = "RedisRestControllerImpl")
@RequestMapping("/redis")
public class RedisRestControllerImpl extends RestBaseController implements RedisRestController {


    @Autowired
    private RedisRestController redisRestController;

    @Override
    @PostMapping("/selectRedis.html")
    public ResponseResult selectRedis(HttpServletRequest request) {
        return redisRestController.selectRedis(request);
    }
}

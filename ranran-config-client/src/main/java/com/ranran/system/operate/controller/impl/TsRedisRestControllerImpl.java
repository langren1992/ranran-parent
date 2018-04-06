package com.ranran.system.operate.controller.impl;

import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.system.operate.controller.TsRedisRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据字典请求控制类
 * @creator zengrui 2018-01-25 11:14
 */
@RestController(value = "TsRedisRestController")
@RequestMapping("/redis")
public class TsRedisRestControllerImpl extends RestBaseController implements TsRedisRestController {


    @Autowired
    private TsRedisRestController redisRestController;

    @Override
    @PostMapping("/selectRedis.html")
    public ResponseResult selectRedis(HttpServletRequest request) {
        return redisRestController.selectRedis(request);
    }

    /**
     * 删除Redis信息
     *
     * @param request 请求信息
     * @return 统一响应信息
     */
    @Override
    @PostMapping("/deleteRedis.html")
    public ResponseResult deleteRedis(HttpServletRequest request) {
        return redisRestController.deleteRedis(request);
    }
}

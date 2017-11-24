package com.ranran.core.controller;/**
 * 测试消息发送
 *
 * @author 曾睿
 * @create 2017-11-16 21:26
 **/

import com.ranran.core.rabbitmq.SenderException;
import com.ranran.core.redis.AbstractRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试消息发送
 * @author
 * @create 2017-11-16 21:26
 **/
@RestController
public class TestRedisController {

    @Autowired
    private AbstractRedis abstractRedis;

    @GetMapping("/redis/set")
    public String queueMq(@RequestParam(value="key",required = true) String key,@RequestParam(value="value",required = true) String value){
        try {
            abstractRedis.set(key,value);
        } catch (SenderException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "aaa";
    }

    @GetMapping("/redis/get")
    public String queue(@RequestParam(value="key",required = true) String key){
        String s = "";
        try {
            s = abstractRedis.get(key);
        } catch (SenderException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}

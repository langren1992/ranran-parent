package com.ranran.core.controller;/**
 * 测试消息发送
 *
 * @author 曾睿
 * @create 2017-11-16 21:26
 **/

import com.ranran.uums.mq.QueueSender;
import com.ranran.uums.mq.Receiver;
import com.ranran.uums.mq.Sender;
import com.ranran.core.rabbitmq.SenderException;
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
public class TestRabbitController    {

    @Autowired
    private Sender sender;

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private Receiver receiver;

    @GetMapping("/rabbit")
    public String queueMq(@RequestParam(value="name",required = true) String name){
        try {
            sender.send(name);
        } catch (SenderException e) {
            e.printStackTrace();
        }
        return "aaa";
    }

    @GetMapping("/queue")
    public String queue(@RequestParam(value="msg",required = true) String msg){
        try {
            queueSender.send(msg);
        } catch (SenderException e) {
            e.printStackTrace();
        }
        return "aaa";
    }
}

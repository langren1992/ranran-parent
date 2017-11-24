package com.ranran.core.rabbitmq;

import com.rabbitmq.client.Channel;

/**
 * 接收者封装
 *
 * @author 曾睿
 * @create 2017-11-19 13:35
 **/
public abstract class AbstractReceiver {

    public abstract void receive(String message,long deliveryTag,Channel channel);
}

package com.ranran.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * 接收者封装
 *
 * @author 曾睿
 * @create 2017-11-19 13:35
 **/
public abstract class AbstractReceiver {

    public abstract void receive(String message,long deliveryTag,Channel channel);
}

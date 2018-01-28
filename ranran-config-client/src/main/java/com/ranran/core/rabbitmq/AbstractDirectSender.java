//package com.ranran.core.rabbitmq;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//
///**
// * @author 曾睿
// * @create 2017-11-19 1:17
// **/
//public abstract class AbstractDirectSender extends AbstractSender{
//
//    public AbstractDirectSender(RabbitTemplate rabbitTemplate) {
//        super(rabbitTemplate);
//    }
//
//    @Override
//    public abstract DirectExchange exchange();
//
//    public abstract Binding binding(DirectExchange directExchange, Queue queue);
//}

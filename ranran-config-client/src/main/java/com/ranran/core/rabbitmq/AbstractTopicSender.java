//package com.ranran.core.rabbitmq;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//
///**
// * @author 曾睿
// * @create 2017-11-19 1:17
// **/
//public abstract class AbstractTopicSender extends AbstractSender{
//
//    public AbstractTopicSender(RabbitTemplate rabbitTemplate) {
//        super(rabbitTemplate);
//    }
//
//    @Override
//    public abstract TopicExchange exchange();
//
//    public abstract Binding binding(TopicExchange topicExchange, Queue queue,String pattern);
//
//    public abstract String getPattern();
//}

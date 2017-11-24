package com.ranran.uums.mq;/**
 * 主题模式测试
 *
 * @author 曾睿
 * @create 2017-11-19 11:20
 **/

import com.ranran.core.rabbitmq.AbstractTopicSender;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 主题模式测试
 * @author
 * @create 2017-11-19 11:20
 **/
public class TopicSender extends AbstractTopicSender {

    public TopicSender(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @Override
    protected String getExChange() {
        return null;
    }

    @Override
    protected String getRoutingKey() {
        return null;
    }

    @Override
    public TopicExchange exchange() {
        return null;
    }

    @Override
    public Queue queue() {
        return null;
    }

    @Override
    public String getPattern() {
        return null;
    }

    @Override
    public Binding binding(TopicExchange topicExchange, Queue queue,String pattern) {
        return BindingBuilder.bind(queue).to(topicExchange).with(pattern);
    }

}

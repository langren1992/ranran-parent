package com.ranran.study.mq;

import com.ranran.core.rabbitmq.AbstractDirectSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * 点对点消费，发送组件
 *
 * @author 曾睿
 * @create 2017-11-18 22:53
 **/
@Component
public class QueueSender extends AbstractDirectSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueSender.class);

    @Autowired
    QueueSender(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @Override
    protected String getExChange() {
        return DirectExchange.DEFAULT.getName();
    }

    @Override
    protected String getRoutingKey() {
        return SenderConstants.QUEUE_SENDER;
    }

    /**
     * 组件Bean名称唯一，不能重复
     * */
    @Override
    @Bean(name = "queueSender.Exchange")
    public DirectExchange exchange() {
        return new DirectExchange(this.getExChange());
    }

    @Bean(name = "queueSender.queue")
    @Override
    public Queue queue() {
        return new Queue(this.getRoutingKey());
    }

    @Bean
    @Override
    public Binding binding(@Qualifier("queueSender.Exchange") DirectExchange directExchange, @Qualifier("queueSender.queue") Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).withQueueName();
    }
}

package com.ranran.study.mq;

import com.ranran.core.rabbitmq.AbstractDirectSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 发送者
 *
 * @author 曾睿
 * @create 2017-11-14 22:50
 **/
@Component
public  class Sender extends AbstractDirectSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    /**
     * 构造方法注入
     * @Autowired 直接根据类型注解参数
     *
     */
    @Autowired
    public Sender(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @Override
    protected String getExChange() {
        return SenderConstants.COM_RANRAN_RABBITMQ_TEST_EXCHANGE;
    }

    @Override
    protected String getRoutingKey() {
        return SenderConstants.RANRAN_ROUTINGKEY;
    }

    @Bean
    @Override
    public DirectExchange exchange(){
        return new DirectExchange(this.getExChange());
    }

    @Bean
    @Override
    public Queue queue(){
        return new Queue(this.getRoutingKey());
    }

    @Bean
    @Override
    public Binding binding(@Qualifier("exchange") DirectExchange directExchange,@Qualifier("queue") Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).withQueueName();
    }

}

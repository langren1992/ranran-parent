package com.ranran.rabbitmq;


import com.ranran.uums.mq.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

/**
 * 抽象发送者
 * @author
 * @create 2017-11-18 17:08
 **/
public abstract class AbstractSender implements  RabbitTemplate.ConfirmCallback{

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    protected RabbitTemplate rabbitTemplate;

    AbstractSender(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    protected abstract String getExChange();

    protected abstract String getRoutingKey();

    public abstract Exchange exchange();

    public abstract Queue queue();

    public void send(String message) throws SenderException {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString()+ " exchange:"+this.getExChange()+" routingKey:"+ this.getRoutingKey());
        this.rabbitTemplate.convertAndSend(this.getExChange(), this.getRoutingKey(), message, correlationId);
    }

    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            LOGGER.info("Sender send {} message success!",correlationData.getId());
        } else {
            LOGGER.error("Sender send {} message fail, cause by : {}",correlationData.getId(),cause);
        }
    }

}

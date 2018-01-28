//package com.ranran.study.mq;
//
///**
// * 接收者
// *
// * @author 曾睿
// * @create 2017-11-14 22:52
// **/
//
//import com.rabbitmq.client.Channel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//@Component
//@RabbitListener(queues = "queue.sender")
//public class QueueReceiver {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(QueueReceiver.class);
//
//    @RabbitHandler
//    public void receiveMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel){
//        LOGGER.info(Thread.currentThread().getName()+" Successfully receive {}'s messages. the message is {}","queue.sender",message);
//    }
//
//
//}
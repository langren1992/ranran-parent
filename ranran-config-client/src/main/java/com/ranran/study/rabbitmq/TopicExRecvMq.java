package com.ranran.study.rabbitmq;


import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * “发布/订阅”模式Publish/Subscribe 接收者
 *
 * @author 曾睿
 * @create 2017-11-15 21:37
 **/
public class TopicExRecvMq {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC,true);
        String queueName = channel.queueDeclare().getQueue();
        String[] strings = new String[]{"1","11","2","22","3","33","4","55","5555"};
        if (strings.length < 1) {
            System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
            System.exit(1);
        }

//        for (String bindingKey : strings) {
            channel.queueBind(queueName, EXCHANGE_NAME, "1.*");
//        }

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

}

//package com.ranran.study.rabbitmq;
//
//
//import com.rabbitmq.client.*;
//
//import java.io.IOException;
//import java.util.UUID;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.TimeoutException;
//
///**
// * “发布/订阅”模式Publish/Subscribe 发送者 必须客户端存在才能接收 如果不在数据丢失
// *
// * @author 曾睿
// * @create 2017-11-15 21:37
// **/
//public class RpcClient {
//
//    private Connection connection;
//    private Channel channel;
//    private String requestQueueName = "rpc_queue";
//    private String replyQueueName;
//
//    public RpcClient() throws IOException, TimeoutException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//
//        connection = factory.newConnection();
//        channel = connection.createChannel();
//
//        replyQueueName = channel.queueDeclare().getQueue();
//    }
//
//    public String call(String message) throws IOException, InterruptedException {
//        final String corrId = UUID.randomUUID().toString();
//
//        AMQP.BasicProperties props = new AMQP.BasicProperties
//                .Builder()
//                .correlationId(corrId)
//                .replyTo(replyQueueName)
//                .build();
//
//        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
//
//        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);
//
//        channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                if (properties.getCorrelationId().equals(corrId)) {
//                    response.offer(new String(body, "UTF-8"));
//                    System.out.println(" handleDelivery"+corrId);
//                }
//            }
//        });
//
//        return response.take();
//    }
//
//    public void close() throws IOException {
//        connection.close();
//    }
//
//    public static void main(String[] argv) {
//        RpcClient fibonacciRpc = null;
//        String response = null;
//        try {
//            fibonacciRpc = new RpcClient();
//
//            System.out.println(" [x] Requesting fib(30)");
//            response = fibonacciRpc.call("30");
//            System.out.println(" [.] Got '" + response + "'");
//        }
//        catch  (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        } finally {
//            if (fibonacciRpc!= null) {
//                try {
//                    fibonacciRpc.close();
//                }
//                catch (IOException _ignore) {}
//            }
//        }
//    }
//}

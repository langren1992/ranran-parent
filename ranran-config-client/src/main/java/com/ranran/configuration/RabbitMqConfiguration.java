package com.ranran.configuration;


import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * RabbitMq远程配置
 *
 * @author 曾睿
 * @create 2017-11-14 0:19
 **/
@Configuration
@RefreshScope
public class RabbitMqConfiguration {

    @Bean(name="rabbitConnectionFactory")
    @Primary
    public ConnectionFactory connectionFactory(
            @Value("${rabbitmq.host}") String host,
            @Value("${rabbitmq.port}") int port,
            @Value("${rabbitmq.username}") String username,
            @Value("${rabbitmq.password}") String password
    ){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean(name="firstRabbitTemplate")
    @Primary
    public RabbitTemplate rabbitTemplate(
            @Qualifier("rabbitConnectionFactory") ConnectionFactory connectionFactory
    ){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean(name="firstFactory")
    public SimpleRabbitListenerContainerFactory factory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("rabbitConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(simpleRabbitListenerContainerFactory, connectionFactory);
        return simpleRabbitListenerContainerFactory;
    }
}

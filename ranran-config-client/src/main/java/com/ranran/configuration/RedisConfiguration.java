package com.ranran.configuration;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis远程配置
 *
 * @author 曾睿
 * @create 2017-11-14 0:19
 **/
@Configuration
@RefreshScope
public class RedisConfiguration {

    @Bean
    public JedisPool jedisPoolConfig(
            @Value("${redis.host}") String hostName,
            @Value("${redis.port}") int port,
            @Value("${redis.password}") String password,
            @Value("${redis.maxIdle}") int maxIdle,
            @Value("${redis.maxTotal}") int maxActive,
            @Value("${redis.maxWait}") long maxWait,
            @Value("${redis.testOnBorrow}") boolean testOnBorrow
    ){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(maxIdle);
        //最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(maxActive);
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        //在获取连接的时候检查有效性, 默认false
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return new JedisPool(jedisPoolConfig,hostName,port,300000,password);
    }

}

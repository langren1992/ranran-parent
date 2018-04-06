package com.ranran;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@EnableDiscoveryClient  //激活eureka中的DiscoveryClient实现
//@RefreshScope           //自动刷新作用域的值
//@EnableCircuitBreaker   //激化熔断器监控
@ComponentScan(basePackages = "com.ranran")
@MapperScan(basePackages = {"com.ranran.system.**.mapper","com.ranran.base.**.mapper","com.ranran.uidevelop.**.mapper"})
@EnableTransactionManagement //开启事务管理
public class ConfigClientApp {

    public static void main( String[] args ) {
        SpringApplication.run(ConfigClientApp.class, args);
    }

}

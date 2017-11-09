package com.ranran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hello world!
 *
 */
@EnableHystrixDashboard     //开启熔断服务
@EnableEurekaClient
@SpringBootApplication
public class HystrixServerApp {
    public static void main( String[] args ) {
        SpringApplication.run(HystrixServerApp.class, args);
    }
}

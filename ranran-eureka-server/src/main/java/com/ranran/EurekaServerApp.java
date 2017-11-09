package com.ranran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer  //启动一个服务注册中心提供给其他应用进行对话
public class EurekaServerApp {
    public static void main( String[] args ) {
        SpringApplication.run(EurekaServerApp.class, args);
    }
}

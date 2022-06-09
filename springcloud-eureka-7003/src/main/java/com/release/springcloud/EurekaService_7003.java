package com.release.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yancheng
 * @since 2022/3/15
 */
@SpringBootApplication
@EnableEurekaServer //它是服务端的启动类，它可以接受别人注册进来
public class EurekaService_7003 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaService_7003.class,args);
    }
}

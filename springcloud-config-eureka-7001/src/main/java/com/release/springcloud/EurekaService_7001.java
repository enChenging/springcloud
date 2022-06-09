package com.release.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务端集群
 * 3个Eureka注册中心，3个集群
 * Eureka AP原则  保证可用性，容错行
 * @author yancheng
 * @since 2022/3/15
 */
@SpringBootApplication
@EnableEurekaServer //它是服务端的启动类，它可以接受别人注册进来
public class EurekaService_7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaService_7001.class,args);
    }
}

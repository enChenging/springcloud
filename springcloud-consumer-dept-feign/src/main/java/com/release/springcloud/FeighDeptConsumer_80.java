package com.release.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yancheng
 * @since 2022/3/15
 */
@SpringBootApplication
@EnableEurekaClient //将服务注册到Eureka中
@EnableFeignClients(basePackages = {"com.release.springcloud"})
public class FeighDeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(FeighDeptConsumer_80.class, args);
    }
}
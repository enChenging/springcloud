package com.release.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yancheng
 * @since 2022/3/15
 */
@SpringBootApplication
@EnableEurekaClient //将服务注册到Eureka中
@EnableDiscoveryClient //注册进来的微服务，获取一些消息
public class DeptProvider_8003 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8003.class, args);
    }
}

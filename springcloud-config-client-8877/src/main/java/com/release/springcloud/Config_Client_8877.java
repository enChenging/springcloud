package com.release.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yancheng
 * @since 2022/3/15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Config_Client_8877 {
    public static void main(String[] args) {
        SpringApplication.run(Config_Client_8877.class, args);
    }
}
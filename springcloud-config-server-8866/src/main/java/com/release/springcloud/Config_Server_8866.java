package com.release.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author yancheng
 * @since 2022/3/15
 */
@SpringBootApplication
@EnableConfigServer
public class Config_Server_8866 {
    public static void main(String[] args) {
        SpringApplication.run(Config_Server_8866.class, args);
    }
}
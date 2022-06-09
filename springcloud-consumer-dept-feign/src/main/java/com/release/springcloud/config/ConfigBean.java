package com.release.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yancheng
 * @since 2022/3/15
 */
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced//Ribbon 配置负载均衡实现RestTemplate
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @Bean
    public IRule myRule(){
        return new RandomRule();
    }

}

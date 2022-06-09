package com.release.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * 服务熔断：服务端  某个服务超时或者异常，引起熔断
 * 服务降级：客户端  从整体网络请求负载考虑，当某个服务熔断或者关闭之后，此时在客户端，我们可以准备一个FallbackFactory，返回一个默认值
 * @author yancheng
 * @since 2022/3/15
 */
//服务熔断
@SpringBootApplication
@EnableEurekaClient //将服务注册到Eureka中
@EnableDiscoveryClient //注册进来的微服务，获取一些消息
@EnableCircuitBreaker //添加熔断的支持
public class DeptProviderHystrix_8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderHystrix_8001.class, args);
    }

    //HystrixDashboard //监控流
    //增加一个Servlet
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        return registrationBean;
    }
}

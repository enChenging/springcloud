package com.release.springcloud.controller;

import com.release.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 调用微服务2种方法:1.微服务名称【ribbon】2.接口和注释【feign】
 * @author yancheng
 * @since 2022/3/15
 */
@RestController
public class DeptConsumerController {
    //消费者，不应该有service层
    //RestTemplate 供我们直接使用 通过http请求
    //(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables)
    @Autowired
    private RestTemplate restTemplate;

//    private static final String REST_URL_PREFIX = "http://localhost:8001";
    //通过Ribbon,我们获取这里的地址，应该是一个变量，通过微服务名来访问
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }


    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

}

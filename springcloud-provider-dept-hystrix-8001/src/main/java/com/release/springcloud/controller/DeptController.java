package com.release.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.release.springcloud.pojo.Dept;
import com.release.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yancheng
 * @since 2022/3/15
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = deptService.queryById(id);
        if (dept == null){
            throw new RuntimeException("id ==>"+id+"，不存在该用户信息");
        }
        return dept;
    }

    //备选方案
    public Dept hystrixGet(@PathVariable("id")Long id){
        return new Dept()
                .setDeptno(id)
                .setDname("id ==>"+id+"，没有对应的信息，null--@Hystrix")
                .setDb_source("no this database in MySQL");
    }

}

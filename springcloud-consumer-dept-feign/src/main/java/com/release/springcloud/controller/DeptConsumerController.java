package com.release.springcloud.controller;

import com.release.springcloud.pojo.Dept;
import com.release.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 调用微服务2种方法:1.微服务名称【ribbon】2.接口和注释【feign】
 * feign本质上调用ribbon
 * @author yancheng
 * @since 2022/3/15
 */
@RestController
public class DeptConsumerController {
    //消费者，不应该有service层
    @Autowired
    private DeptClientService deptClientService;

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return this.deptClientService.addDept(dept);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return this.deptClientService.queryById(id);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        return this.deptClientService.queryAll();
    }

}

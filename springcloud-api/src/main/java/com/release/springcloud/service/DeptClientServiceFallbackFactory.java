package com.release.springcloud.service;

import com.release.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务熔断：服务端  某个服务超时或者异常，引起熔断
 * 服务降级：客户端  从整体网络请求负载考虑，当某个服务熔断或者关闭之后，此时在客户端，我们可以准备一个FallbackFactory，返回一个默认值
 *
 * @author yancheng
 * @since 2022/3/16
 */
//服务降级
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean addDept(Dept dept) {
                return false;
            }

            @Override
            public Dept queryById(Long id) {
                return new Dept()
                        .setDeptno(id)
                        .setDname("id ==>" + id + "，没有对应的信息，null--@Hystrix")
                        .setDb_source("no this database in MySQL");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }
        };
    }
}

package com.release.springcloud.service;

import com.release.springcloud.pojo.Dept;

import java.util.List;

/**
 * @author yancheng
 * @since 2022/3/15
 */
public interface DeptService {

    public boolean addDept(Dept dept);

    public Dept queryById(Long id);

    public List<Dept> queryAll();
}

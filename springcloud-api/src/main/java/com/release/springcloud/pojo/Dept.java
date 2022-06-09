package com.release.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yancheng
 * @since 2022/3/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {

    private static final long serialVersionUID = -8343856046675415168L;
    private Long deptno;
    private String dname;
    //数据来自哪个数据库
    private String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }

}


CREATE DATABASE db01;

USE db03;

create table dept
(
    deptno    bigint auto_increment
        primary key,
    dname     varchar(60) null,
    db_source varchar(60) null
)COMMENT = '部门表';

INSERT INTO `dept` VALUES (1, '开发部', DATABASE());
INSERT INTO `dept` VALUES (2, '人事部', DATABASE());
INSERT INTO `dept` VALUES (3, '财务部', DATABASE());
INSERT INTO `dept` VALUES (4, '市场部', DATABASE());
INSERT INTO `dept` VALUES (5, '运维部', DATABASE());
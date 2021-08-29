-- -----------------
-- 创建相关数据库
-- -----------------
DROP DATABASE IF EXISTS `schooldb`;
CREATE DATABASE `schooldb`;
USE `schooldb`;

-- -----------------
-- 创建相关表
-- -----------------

-- -----------------
-- 创建用户表
-- -----------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (

    account        VARCHAR(20)        NOT NULL      COMMENT '用户账号',
    password       VARCHAR(50)        NOT NULL      COMMENT '密码',
    user_type      TINYINT(5)                       COMMENT '用户类型,0代表超级管理员，1代表管理员，2代表学生，3代表商家',
    create_time    DATETIME                         COMMENT '创建时间',
    update_time    DATETIME                         COMMENT '修改时间'

) ENGINE=innodb COMMENT = '用户表';

-- -----------------
-- 创建学生表
-- -----------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (

    account         VARCHAR(20)        NOT NULL      COMMENT '学号',
    password        VARCHAR(50)        NOT NULL      COMMENT '密码',
    create_time     DATETIME                         COMMENT '创建时间',
    update_time     DATETIME                         COMMENT '修改时间'


) ENGINE=innodb COMMENT = '学生表';

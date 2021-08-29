package com.school.pojo;

import java.util.Date;

/**
 * 用户信息实体
 *
 * @author hnuer
 */
public class User {


    /**
     * 用户账号
     */
    private String account;


    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户类型，0代表超级管理员，1代表普通管理员
     * 2代表学生，3代表商家
     */
    private int userType;


    /**
     * 用户信息创建时间
     */
    private Date createTime;

    /**
     * 用户信息修改时间
     */
    private Date updateTime;

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User(String account, String password, int userType) {
        this.account = account;
        this.password = password;
        this.userType = userType;
    }

    public User(String account, String password, int userType, Date createTime, Date updateTime) {
        this.account = account;
        this.password = password;
        this.userType = userType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}

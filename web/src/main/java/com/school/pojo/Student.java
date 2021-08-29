package com.school.pojo;

import java.util.Date;

/**
 * 学生实体
 *
 * @author hnuer
 */
public class Student {

    /** 学生学号 */
    private String account;

    /** 学生密码 */
    private String password;

    /** 学生信息创建时间 */
    private Date createTime;

    /** 学生信息修改时间 */
    private Date updateTime;

    public Student() {

    }

    public Student(String account, String password) {
        this.account = account;
        this.password = password;
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

    @Override
    public String toString() {
        return "Student{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

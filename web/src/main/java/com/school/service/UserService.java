package com.school.service;

import com.school.pojo.User;

/**
 * 用户管理相关服务
 *
 * @author hnuer
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     * @param account
     * @return 用户对象信息
     */
    User selectUserByAccount(String account);

    /**
     * 添加用户信息
     * @param user
     * @return 操作结果
     */
    int addUser(User user);

}

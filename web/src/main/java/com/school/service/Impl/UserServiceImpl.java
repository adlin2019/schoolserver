package com.school.service.Impl;

import com.school.mapper.UserMapper;
import com.school.pojo.User;
import com.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户管理服务实现
 *
 * @author hnuer
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByAccount(String account) {
        return userMapper.selectUserByAccount(account);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }
}

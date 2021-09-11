package com.school.mysql;

import com.school.mapper.UserMapper;
import com.school.pojo.User;
import com.school.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 操作mysql数据库
 */
@SpringBootTest
public class SQLTest {


    @Autowired
    private UserMapper userMapper;


    /**
     * 向数据库添加用户信息
     */
    @Test
    void addUser() {
        User user = new User("hello", "123", 2);
        //对user的密码进行加密处理
        String encodedPassword = SecurityUtils.encryptPassword(user.getPassword());
        user.setPassword(encodedPassword);
        int rows = userMapper.addUser(user);
        System.out.println("插入了" + rows + "条数据");
    }



    /**
     * 根据用户账号删除数据行
     */
    @Test
    void deleteRowByAccount() {
        String account = "hello";
        int rows = userMapper.deleteUserByAccount(account);
        System.out.println("一共" + rows + "行被删除");
    }

}

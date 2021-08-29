package com.school.mapper;

import com.school.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 检索用户相关接口
 *
 * @author hnuer
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名（账号）查询用户对象
     * @param account
     * @return 用户对象
     */
    User selectUserByAccount(String account);

    /**
     * 根据用户信息向表中添加账号
     * @param user
     * @return 操作结果
     */
    int addUser(User user);



}

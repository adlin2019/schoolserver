package com.school.service;

import com.school.pojo.LoginBody;
import com.school.pojo.Student;

/**
 * 登录服务相关接口
 *
 * @author hnuer
 */
public interface LoginService {



    /**
     * 对登录实体进行校验，并返回token
     * @param loginBody
     * @return token
     */
    String verify(LoginBody loginBody);




}

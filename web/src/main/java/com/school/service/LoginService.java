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
     * 添加学生信息
     * @param student
     * @return
     */
    int addStudent(Student student);


    /**
     * 对登录实体进行校验
     * @param loginBody
     * @return
     */
    int verify(LoginBody loginBody);


    /**
     * 根据登录信息，生成登录对象
     * @param loginBody
     * @return
     */
    String createToken(LoginBody loginBody);

}

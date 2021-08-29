package com.school.mapper;

import com.school.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * 对学生表操作相关接口
 *
 * @author hnuer
 */
@Mapper
public interface StudentMapper {


    /**
     * 通过学生帐号查询学生信息
     * @param account
     * @return
     */
    Student selectStudentByAccount(String account);


    /**
     * 添加学生信息
     * @param student
     * @return
     */
    int addStudent(Student student);


}

package com.school.controller;

import com.school.pojo.ResInfo;
import com.school.pojo.User;
import com.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理相关接口
 *
 * @author adlin
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public ResInfo addUser(@RequestBody User user) {

        int result = userService.addUser(user);

        return ResInfo.success("成功新增" + result + "位用户");

    }

}

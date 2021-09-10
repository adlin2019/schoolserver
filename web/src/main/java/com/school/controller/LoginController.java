package com.school.controller;


import com.school.component.TokenService;
import com.school.constant.Constants;
import com.school.pojo.LoginBody;
import com.school.pojo.ResInfo;
import com.school.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录相关接口
 *
 * @author hnuer
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * 实现登录校验，并返回token
     * @param loginBody
     * @return
     */
    @PostMapping("/submit")
    public ResInfo submit(@RequestBody LoginBody loginBody) {


        // 创建返回实体
        ResInfo res = ResInfo.success();

        // 登录校验并返回token
        String token = loginService.verify(loginBody);

        res.put(Constants.TOKEN, token);

        return res;

    }


}

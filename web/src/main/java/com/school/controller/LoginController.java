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

    @Autowired
    private TokenService tokenService;



    @PostMapping("/submit")
    public ResInfo submit(@RequestBody LoginBody loginBody) {


        // 创建返回实体
        ResInfo res = ResInfo.success();

        // 对登录实体进行校验
        int result = loginService.verify(loginBody);

        // 根据返回的result判断校验结果
        // result为0，说明校验失败，直接返回结果
        if (result == 0) {
            res.put("result", "登录失败");
            return res;
        }

        // 校验通过则创建与用户对应的token
        String token = loginService.createToken(loginBody);
        res.put(Constants.TOKEN, token);

        return res;

    }


}

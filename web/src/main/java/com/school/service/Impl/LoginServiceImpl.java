package com.school.service.Impl;

import com.school.component.RedisService;
import com.school.component.TokenService;
import com.school.constant.Constants;
import com.school.exception.KaptchaException;
import com.school.exception.ValidateException;
import com.school.mapper.UserMapper;
import com.school.pojo.LoginBody;
import com.school.pojo.LoginUser;
import com.school.pojo.Student;
import com.school.pojo.User;
import com.school.service.LoginService;
import com.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;
import java.util.Date;

/**
 * 登录接口实现类
 *
 * @author hnuer
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;



    @Override
    public String verify(LoginBody loginBody) {

        // 1. 得到登录信息
        String account = loginBody.getAccount();
        String password = loginBody.getPassword();
        String code = loginBody.getCode();
        String uuid = loginBody.getUuid();

        // 2. 对验证码进行校验
        // 得到验证码存储的key
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String capText = redisService.getCacheObject(verifyKey);
        redisService.deleteObject(verifyKey);

        // 先判断是否有对应的验证码
        if (capText == null) {
            throw new KaptchaException("传入验证码为空！");
        }

        // 判断用户传来的验证码是否正确
        if (!code.equalsIgnoreCase(capText)) {
            throw new KaptchaException("验证码输入错误！");
        }

        // 3.对用户的账号密码进行校验
        Authentication authentication = null;
        try {
            // 通过authenticationManager接口进行校验
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account, password));
        } catch (Exception e) {
            throw new ValidateException("帐号或者密码输入错误");
        }

        // 4.返回token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        return createToken(loginUser);
    }

    public String createToken(LoginUser loginUser) {
        //创建token
        return tokenService.createToken(loginUser);






    }
}

package com.school.service.Impl;

import com.school.component.RedisService;
import com.school.component.TokenService;
import com.school.constant.Constants;
import com.school.exception.KaptchaException;
import com.school.exception.ValidateException;
import com.school.pojo.LoginBody;
import com.school.pojo.LoginUser;
import com.school.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;



    @Override
    public String verifyAndGetToken(LoginBody loginBody) {

        // 1. 得到登录信息
        String account = loginBody.getAccount();
        String password = loginBody.getPassword();
        String code = loginBody.getCode();
        String uuid = loginBody.getUuid();

        // 2. 对验证码进行校验校验
        verifyKaptcha(code, uuid);

        // 3.对用户的账号密码进行校验，并返回LoginUser的信息
        LoginUser loginUser = verifyAccountAndPassword(account, password);

        // 4.返回token
        return createToken(loginUser);
    }

    /**
     * 对用户名和密码进行校验
     * @param account 用户账号
     * @param password 用户密码
     * @return 登录用户
     */
    private LoginUser verifyAccountAndPassword(String account, String password) {
        Authentication authentication = null;
        try {
            // 通过authenticationManager接口进行校验
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account, password));
        } catch (Exception e) {
            throw new ValidateException("用户名不存在/密码错误！");
        }
        return (LoginUser) authentication.getPrincipal();
    }

    /**
     * 对验证码进行校验
     * @param code 用户传来的二维码
     * @param uuid 二维码的唯一标识
     */
    private void verifyKaptcha(String code, String uuid) {
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
    }

    /**
     * 根据登录用户的信息创建token
     * @param loginUser
     * @return
     */
    private String createToken(LoginUser loginUser) {
        //创建token
        return tokenService.createToken(loginUser);

    }
}

package com.school.service.Impl;

import com.school.component.RedisService;
import com.school.component.TokenService;
import com.school.constant.Constants;
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
    public int addStudent(Student student) {
        return 0;
    }

    @Override
    public int verify(LoginBody loginBody) {

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
            // 0表示校验失败
            return 0;
        }

        // 判断用户传来的验证码是否正确
        if (!code.equalsIgnoreCase(capText)) {
            return 0;
        }


        // 3.对用户的账号密码进行校验
        Authentication authentication = null;
        try {
            // 通过authenticationManager接口进行校验
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account, password));
        } catch (Exception e) {
            System.out.println("认证失败");
            return 0;

        }

        // 认证成功
        return 1;




    }

    @Override
    public String createToken(LoginBody loginBody) {

        //1.构造出LoginUser实体
        LoginUser loginUser = new LoginUser();
        User user = new User(loginBody.getAccount(), loginBody.getPassword(),1);
        loginUser.setUser(user);

        //2.创建token
        return tokenService.createToken(loginUser);






    }
}

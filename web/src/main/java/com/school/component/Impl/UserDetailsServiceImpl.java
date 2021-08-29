package com.school.component.Impl;

import com.school.pojo.LoginUser;
import com.school.pojo.User;
import com.school.service.UserService;
import com.school.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理逻辑
 *
 * @author hnuer
 */
@Service
@Qualifier("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    /**
     * 获得用户认证信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户
        User user = userService.selectUserByAccount(username);
        if (StringUtils.isNull(user)) {
            //如果用户不存在
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + "不存在");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(User user) {

        return new LoginUser(user);

    }
}

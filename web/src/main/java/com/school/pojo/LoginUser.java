package com.school.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录用户实体
 *
 * @author ruoyi
 */
public class LoginUser implements UserDetails {


    private static final long serialVersionUID = 1L;


    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 用户信息
     */
    private User user;

    public LoginUser(String token, Long loginTime, Long expireTime, User user) {
        this.uuid = token;
        this.loginTime = loginTime;
        this.expireTime = expireTime;
        this.user = user;
    }

    public LoginUser(User user) {
        this.user = user;
    }

    public LoginUser() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "uuid='" + uuid + '\'' +
                ", loginTime=" + loginTime +
                ", expireTime=" + expireTime +
                ", user=" + user +
                '}';
    }

    /**
     * 获取用户名（用户账号）
     * @return
     */
    @Override
    public String getUsername() {
        return user.getAccount();
    }

    /**
     * 获取用户密码
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 获取用户权限集合
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 是否可用，禁用的用户不能身份验证
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 账户是否未过期,过期无法验证
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * 指定用户是否解锁，所用的用户无法进行身份验证
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}

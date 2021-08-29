package com.school.pojo;

/**
 * 登录实体
 *
 * @author ruoyi
 */
public class LoginBody {

    /**
     * 用户名
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码内容
     */
    private String code;

    /**
     * 验证码的唯一标识
     */
    private String uuid = "";

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }



}

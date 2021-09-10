package com.school.component;

import com.school.constant.Constants;
import com.school.pojo.LoginUser;
import com.school.utils.IdUtils;
import com.school.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * token相关服务
 *
 * @author ruoyi
 */
@Component
public class TokenService {

    /**
     *  令牌自定义标识，配置文件里的值：Authorization
     */
    @Value("${token.header}")
    private String header;

    /**
     * 令牌秘钥。配置文件里面的值：abcdefghijklmnopqrstuvwxyz
     */
    @Value("${token.secret}")
    private String secret;

    /**
     * 令牌有效时间（默认30分钟），配置文件里面的值：30
     */
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private RedisService redisService;


    /**
     * 为登录的用户创建token
     *
     * @param loginUser
     * @return token
     */
    public String createToken(LoginUser loginUser) {

        // 1.为登录用户设置唯一标识
        String uuid = IdUtils.fastUUID();
        loginUser.setUuid(uuid);

        // 2.设置用户的缓存时间并进行缓存（loginUser的缓存时间其实就是token的有效时间）
        cacheAndSetExpireTime(loginUser);

        // 3.构造数据声明，此数据声明包含了登录用户的唯一标识
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, uuid);

        // 4.返回一个加密的token
        return createToken(claims);

    }


    /**
     * 设置用户的缓存时间并进行缓存（可以调用此方法刷新缓存时间）
     * @param loginUser
     */
    public void  cacheAndSetExpireTime(LoginUser loginUser) {

        // 设置登录和过期时间
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将登录用户信息缓存，并指定缓存的有效时间
        String userKey = getTokenKey(loginUser.getUuid());
        redisService.setCacheObject(userKey,loginUser,expireTime, TimeUnit.MINUTES);

    }



    /**
     * 从数据声明中生成令牌（token）
     * @param claims
     * @return
     */
    private String createToken(Map<String, Object> claims) {

        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return token;

    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * 生成登录用户对应的key
     * @param uuid
     * @return
     */
    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        // 如果token不为空
        if (StringUtils.isNotEmpty(token))
        {
            // 解析token，得到数据声明
            Claims claims = parseToken(token);
            // 解析对应的权限以及用户信息，uuid是用户的唯一标识符
            String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
            String userKey = getTokenKey(uuid);
            //根据userKey找到对应的user对象
            LoginUser user = redisService.getCacheObject(userKey);
            return user;

        }
        return null;
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        // 从请求头中得到token
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX))
        {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            cacheAndSetExpireTime(loginUser);
        }
    }
}

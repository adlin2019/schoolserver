package com.school.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis相关服务
 *
 * @author hnuer
 */
@Component
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 缓存基本的对象
     *
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value) {

        redisTemplate.opsForValue().set(key, value);

    }


    /**
     * 缓存基本的对象，同时可以指定缓存的有效时间
     *
     * @param key
     * @param value
     * @param timeout 缓存的有效时间
     * @param timeUnit
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {

        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);

    }


    /**
     * 得到缓存对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(final String key) {


        ValueOperations<String, T> operations = redisTemplate.opsForValue();

        return operations.get(key);


    }


    /**
     * 删除单个对象
     *
     * @param key
     * @return
     */
    public boolean deleteObject(final String key) {

        return redisTemplate.delete(key);
    }


}

package com.school.redis;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() {


        //向redis中添加数据（对简单类型数据的操作）
        redisTemplate.opsForValue().set("keys", "value值");

        //向redis中添加set类型的数据
        redisTemplate.opsForSet().add("dataSet", "xiaoMing", "xiaoWang");


        //根据键值取出数据
        System.out.println(redisTemplate.opsForValue().get("keys"));

        //获得dataSet的size
        System.out.println(redisTemplate.opsForSet().size("dataSet"));

        //获得dataSet的所有成员
        System.out.println(redisTemplate.opsForSet().members("dataSet"));


    }


}

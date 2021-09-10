package com.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
/**
 * 测试相关接口
 *
 * @author hnuer
 */
public class TestController {

    @GetMapping("/test01")
    public String test01() {
        return "hello world!";
    }

    @GetMapping("/test02")
    public String test02() {
        return "你拥有更高的权限！成功访问此接口！";
    }
}

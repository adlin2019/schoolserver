package com.school.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "TestController", description = "测试相关接口")
public class TestController {

    @ApiOperation("测试接口1")
    @GetMapping("/test01")
    public String test01() {
        return "hello world!";
    }

    @ApiOperation("测试接口2")
    @GetMapping("/test02")
    public String test02() {
        return "你拥有更高的权限！成功访问此接口！";
    }
}

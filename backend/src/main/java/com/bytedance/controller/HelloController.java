package com.bytedance.controller;

import com.bytedance.entity.User;
import com.bytedance.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例代码
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Result<String> hello(){
        return Result.success("hello, world !");
    }

    @GetMapping("/user1")
    public Result<User> hello1(){
        return Result.success(new User(10001, "user1", "123456"));
    }

    @GetMapping("/user2")
    public Result<User> hello2(){
        return Result.fail(new User(10002, "user2", "123456"));
    }
}

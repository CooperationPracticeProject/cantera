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
        return Result.of(Result.ResultCode.FAIL,"hello world !");
    }

    @GetMapping("/user1")
    public Result<User> user1(){
        return Result.of(Result.ResultCode.SUCCESS,new User());
    }

    @GetMapping("/user2")
    public Result<User> user2(){
        return Result.of(Result.ResultCode.USER_NOT_EXISTS,null);
    }

    @GetMapping("/user3")
    public Result<User> user3(){
        return Result.of(3024,"这是自定义状态码返回示例",new User());
    }
}

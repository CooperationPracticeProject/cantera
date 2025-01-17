package com.bytedance.controller;

import com.bytedance.entity.User;
import com.bytedance.service.UserService;
import com.bytedance.service.impl.UserServiceImpl;
import com.bytedance.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/17 下午1:03
 * @description: AuthController类
 */

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public Result<User> login(String username, String password) {
        User user = userService.login(username, password);
        System.out.println("查询后的结果user: "+user);
        if (user == null){
            return Result.of(Result.ResultCode.USER_LOGIN_ERROR,null);
        }
        return Result.of(Result.ResultCode.SUCCESS,user);
    }
}

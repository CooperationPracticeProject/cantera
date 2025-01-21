package com.bytedance.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.bytedance.entity.User;
import com.bytedance.service.UserService;
import com.bytedance.service.impl.UserServiceImpl;
import com.bytedance.util.LoginFormDTO;
import com.bytedance.util.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/17 下午1:03
 * @description: AuthController类
 */

@RestController
@RequestMapping ("/auth")
public class AuthController {

  @Resource
  private UserService userService;

  @PostMapping("/sendMsg")
  public Result<String> sendMsg(@RequestParam("email") String email, HttpSession session) {
    // 发送邮件验证码并保存
    return userService.sendMsg(email, session);
  }

  @PostMapping ("/login")
  public Result<User> login (@RequestBody LoginFormDTO loginFormDTO, HttpSession session) {
    return userService.login(loginFormDTO, session);
  }


  @SaCheckLogin
  @PostMapping ("/logout")
  public Result<String> logout (HttpSession session) {
    return userService.logout(session);
  }


}

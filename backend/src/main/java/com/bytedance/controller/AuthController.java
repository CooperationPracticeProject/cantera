package com.bytedance.controller;

import java.util.Map;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytedance.model.dto.LoginFormDTO;
import com.bytedance.model.vo.UserVo;
import com.bytedance.service.UserService;
import com.bytedance.util.Result;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.temp.SaTempUtil;
import cn.hutool.json.JSONObject;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Resource
  private UserService userService;
  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @PostMapping("/sendMsg")
  public Result<String> sendMsg(@Email String email) {
    return userService.sendMsg(email);
  }

  @PostMapping("/login")
  public Result<UserVo> login(@RequestBody @Validated LoginFormDTO loginFormDTO) {
    return userService.login(loginFormDTO);
  }

  @PostMapping("/refreshToken")
  public Result<String> refreshToken(@RequestBody JSONObject object) {
    String token = (String) object.get("token");
    Object userId = SaTempUtil.parseToken(token);
    if (userId == null) {
      return Result.of(3001, "无效 refreshToken", null);
    }
    String accessToken = StpUtil.createLoginSession(userId);

    return Result.success(Result.ResultCode.SUCCESS.getMessage(), accessToken);
  }

  @SaCheckLogin
  @PostMapping("/logout")
  public Result<String> logout(HttpSession session) {
    return userService.logout(session);
  }

}

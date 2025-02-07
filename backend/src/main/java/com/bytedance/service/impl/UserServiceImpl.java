package com.bytedance.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.model.entity.User;
import com.bytedance.service.EmailService;
import com.bytedance.service.UserService;
import com.bytedance.mapper.UserMapper;
import com.bytedance.model.dto.LoginFormDTO;
import com.bytedance.util.RegexUtils;
import com.bytedance.util.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;


import java.util.concurrent.TimeUnit;

/**
 * @author darling
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2025-01-17 22:30:41
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
  implements UserService {

  @Resource
  private UserMapper userMapper;

  @Resource
  private EmailService emailService;

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Override
  public Result<User> login(LoginFormDTO loginFormDTO, HttpSession session) {
    return null;
  }

  @Override
  public Result<String> sendMsg(String email, HttpSession session) {
    // 1 校验邮箱格式
    if (RegexUtils.isEmailInvalid(email)) {
      return Result.of(1000, "邮箱格式不正确",null);
    }
    // 2 生成验证码
    String code = RandomUtil.randomNumbers(6);
    // 3 保存验证码到redis
    stringRedisTemplate.opsForValue().set("login_code:" + email, code, 5, TimeUnit.MINUTES);
    // 4 发送验证码
      if (!emailService.sendMail(code, email)) {
        return Result.of(1000, "发送验证码失败", null);
      }

    return Result.of(200, "发送验证码成功", null);
  }

  @Override
  public Result<String> logout(HttpSession session) {
    StpUtil.logout();
    return Result.of(200, "退出登录成功", null);
  }


  private User createUserWithEmail(String Email) {
    User user = new User();
    user.setEmail(Email);
    user.setNickname(RandomUtil.randomString(10));
    save(user);
    return user;
  }
}

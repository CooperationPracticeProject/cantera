package com.bytedance.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.entity.User;
import com.bytedance.service.EmailService;
import com.bytedance.service.UserService;
import com.bytedance.mapper.UserMapper;
import com.bytedance.util.LoginFormDTO;
import com.bytedance.util.RegexUtils;
import com.bytedance.util.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
  public Result<User> login (LoginFormDTO loginFormDTO, HttpSession session) {

    String email = loginFormDTO.getEmail();
    // 校验邮箱格式
    if (RegexUtils.isEmailInvalid(email)) {
      return Result.of(1000, "邮箱格式不正确", null);
    }
    // 校验验证码
    String code = loginFormDTO.getCode();
    String redisCode = stringRedisTemplate.opsForValue().get("login_code:" + email);
    if (redisCode == null || !redisCode.equals(code)) {
      return Result.of(1000, "验证码错误", null);
    }
    // 验证码正确，查询用户
    User user = query().eq("email", email).one();
    // 如果用户不存在，注册用户
    if (user == null){
        user = createUserWithEmail(email);
    }
    // sa-token登录
    StpUtil.login(user.getId());
    // 存储用户信息到redis
    Map<String, Object> userMap = BeanUtil.beanToMap(user, new HashMap<>(), CopyOptions.create()
            .setIgnoreNullValue(true).setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
    String redisKey = "user:" + user.getId();
    stringRedisTemplate.opsForHash().putAll(redisKey, userMap);
    // 设置过期时间
    stringRedisTemplate.expire(redisKey, 30, TimeUnit.MINUTES);
    return Result.of(200, "登录成功", user);

//    // md5 加密
//    password = DigestUtils.md5Hex(password);
//    System.out.println("加密后的密码：" + password);
//
//    QueryWrapper<User> wrapper = new QueryWrapper<User>()
//      .eq("username", username)
//      .eq("password", password);
//    return userMapper.selectOne(wrapper);

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

package com.bytedance.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.mapper.UserMapper;
import com.bytedance.model.dto.LoginFormDTO;
import com.bytedance.model.entity.User;
import com.bytedance.model.vo.UserVo;
import com.bytedance.service.EmailService;
import com.bytedance.service.UserService;
import com.bytedance.util.Result;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.temp.SaTempUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

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
  public Result<UserVo> login(LoginFormDTO loginFormDTO) {
    String email = loginFormDTO.getEmail();
    String code = loginFormDTO.getCode();
    String redisCode = stringRedisTemplate.opsForValue().get("login_code:" + email);
    if (redisCode == null || !redisCode.equals(code)) {
      return Result.error(Result.ResultCode.FAILED, "登录失败，登录信息有误");
    }
    // 验证码正确，查询用户
    User user = query().eq("email", email).one();
    // sa-token登录
    StpUtil.login(user.getId());
    // 存储用户信息到redis
    Map<String, Object> userMap = BeanUtil.beanToMap(user, new HashMap<>(), CopyOptions.create()
        .setIgnoreNullValue(true).setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
    String redisKey = "user:" + user.getId();
    stringRedisTemplate.opsForHash().putAll(redisKey, userMap);
    // 设置过期时间
    stringRedisTemplate.expire(redisKey, 30, TimeUnit.MINUTES);
    UserVo userVo = new UserVo();
    BeanUtils.copyProperties(user, userVo);
    userVo.setAccessToken(StpUtil.getTokenValue());
    userVo.setRefreshToken(SaTempUtil.createToken(user.getId(), 2592000));
    return Result.of(200, "登录成功", userVo);
  }

  @Override
  public Result<String> sendMsg(String email) {
    String code = RandomUtil.randomNumbers(6);
    // 3 保存验证码到redis
    stringRedisTemplate.opsForValue().set("login_code:" + email, code, 5, TimeUnit.MINUTES);
    if (!emailService.sendMail(code, email)) {
      return Result.error(Result.ResultCode.FAILED, "发送验证码失败");
    }

    return Result.success("发送验证码成功");
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

package com.bytedance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bytedance.model.entity.User;
import com.bytedance.mapper.UserMapper;
import com.bytedance.service.EmailService;
import com.bytedance.util.FileUpload;
import com.bytedance.service.impl.UserServiceImpl;
import com.bytedance.util.LoginFormDTO;
import com.bytedance.util.Result;
import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 示例代码
 */
@RestController
public class HelloController {

  @Resource
  private UserServiceImpl userService;

  @Resource
  EmailService emailService;

  @Resource
  FileUpload fileUpload;

  @Resource
  private UserMapper userMapper;

  @GetMapping ("/hello")
  public Result<String> hello () {
    return Result.of(Result.ResultCode.FAIL, "hello world !");
  }

  @GetMapping ("/user1")
  public Result<User> user3 () {
    return Result.of(3024, "这是自定义状态码返回示例", new User());
  }

  @GetMapping ("/user3")
  public Result<User> queryUser(@RequestParam(value = "username", defaultValue = "zhangsan") String username,
                                @RequestParam(value = "password", defaultValue = "123456") String password){
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", username);
    queryWrapper.eq("password", DigestUtils.md5Hex(password));//md5加密
    User user = userMapper.selectOne(queryWrapper);
    return Result.of(Result.ResultCode.SUCCESS, user);
  }

  @GetMapping ("/test1")
  public Result<User> test1 () {
    LoginFormDTO loginFormDTO = new LoginFormDTO();
    loginFormDTO.setEmail("example1@gmail.com");
    loginFormDTO.setCode("123456");
    Result<User> user = userService.login(loginFormDTO, null);
    System.out.println("找到user: " + user.getData());
    return user;
//    User user = userService.login("alice", "password");
//    System.out.println("找到user: " + user);
//    return Result.of(Result.ResultCode.SUCCESS, user);
  }

  @GetMapping ("/email")
  public Result<String> email (
    @RequestParam ("code") String code,
    @RequestParam ("email") String email
  ) {
    boolean status = emailService.sendMail(code, email);

    if (status) {
      return Result.of(Result.ResultCode.SUCCESS, "发送成功");
    }
    else {
      return Result.of(Result.ResultCode.FAIL, "发送失败");
    }

  }


  @PostMapping ("/upload")
  public Result<String> upload (
    @RequestParam ("file") MultipartFile file) {
    return fileUpload.upload(file);
  }


}

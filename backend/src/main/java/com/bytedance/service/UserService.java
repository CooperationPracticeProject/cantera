package com.bytedance.service;

import com.bytedance.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.util.LoginFormDTO;
import com.bytedance.util.Result;
import jakarta.servlet.http.HttpSession;

/**
 * @author darling
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2025-01-17 22:30:41
 */
public interface UserService extends IService<User> {

  Result<User> login (LoginFormDTO loginFormDTO, HttpSession session);

  Result<String> sendMsg(String email, HttpSession session);
}

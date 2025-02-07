package com.bytedance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.model.dto.LoginFormDTO;
import com.bytedance.model.entity.User;
import com.bytedance.model.vo.UserVo;
import com.bytedance.util.Result;

import jakarta.servlet.http.HttpSession;

public interface UserService extends IService<User> {

  Result<UserVo>  login(LoginFormDTO loginFormDTO);

  Result<String> sendMsg(String email);

  Result<String> logout(HttpSession session);
}

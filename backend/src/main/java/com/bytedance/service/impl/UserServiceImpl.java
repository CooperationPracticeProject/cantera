package com.bytedance.service.impl;

import com.bytedance.entity.User;
import com.bytedance.mapper.UserMapper;
import com.bytedance.service.UserService;
import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        //md5 加密
        password = DigestUtils.md5Hex(password);
        System.out.println("加密后的密码：" + password);
        return userMapper.findByUsernameAndPassword(username, password);
    }
}

package com.bytedance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.entity.User;
import com.bytedance.service.UserService;
import com.bytedance.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
* @author darling
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-01-17 22:30:41
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{


    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        //md5 加密
        password = DigestUtils.md5Hex(password);
        System.out.println("加密后的密码：" + password);

        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username", username)
                .eq("password", password);
        return userMapper.selectOne(wrapper);
    }
}





package com.bytedance.service;

import com.bytedance.entity.User;

public interface UserService {

    User login(String username, String password);
}

package com.bytedance.entity;

import lombok.Data;
import java.util.Date;

/**
 * @author: 繁星_逐梦
 * @date: 2025/01/16/ 16:08
 * @description: 用户类
 */
@Data
public class User {
    // 用户ID
    private Long id;

    // 用户名
    private String username;

    // 密码(加密)
    private String password;

    // 手机号
    private String phone;

    // 昵称
    private String nickname;

    // 头像URL
    private String avatar;

    // 状态(0:禁用 1:启用)
    private Integer status;

    // 用户角色(1:买家 2:卖家 3:管理员 4:超级管理员)
    private Integer role;

    // 创建时间
    private Date createdAt;

    // 更新时间
    private Date updatedAt;
}
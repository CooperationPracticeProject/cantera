package com.bytedance.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

import java.util.Date;

/**
 * @author: 繁星_逐梦
 * @date: 2025/01/16/ 16:08
 * @description: 用户类
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
  @TableField ("role_id")
  private Long roleId;

  // 创建时间
  @TableField ("created_at")
  private Date createdAt;

  // 更新时间
  @TableField ("updated_at")
  private Date updatedAt;

  // 邮箱
  private String email;
}

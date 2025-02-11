package com.bytedance.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Role {

    // 角色ID
    private Long id;

    // 角色名
    @TableField("role_name")
    private String roleName;

}

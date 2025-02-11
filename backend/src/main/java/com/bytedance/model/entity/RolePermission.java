package com.bytedance.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role_permission")
public class RolePermission {
    // 角色ID
    @TableField("role_id")
    private Long roleId;

    // 权限ID
    @TableField("permission_id")
    private Long permissionId;
}

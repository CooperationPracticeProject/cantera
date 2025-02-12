package com.bytedance.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Permission {

    // 权限ID
    private Long id;

    // 权限名称
    @TableField("perm_name")
    private String permName;
}

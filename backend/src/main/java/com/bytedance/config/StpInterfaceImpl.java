package com.bytedance.config;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bytedance.mapper.PermissionMapper;
import com.bytedance.mapper.RoleMapper;
import com.bytedance.mapper.RolePermissionMapper;
import com.bytedance.mapper.UserMapper;
import com.bytedance.model.entity.Permission;
import com.bytedance.model.entity.RolePermission;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        // 把Object类型转换为long类型
        long userId = Long.parseLong(loginId.toString());
        long roleId = userMapper.selectById(userId).getRoleId();
        // 根据角色id查询权限id
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(
                new QueryWrapper<RolePermission>().eq("role_id", roleId));
        // 根据权限id查询权限名称存入列表
        for(RolePermission rolePermission : rolePermissions)
        {
            Permission permission = permissionMapper.selectById(rolePermission.getPermissionId());
            if (permission != null) {
                list.add(permission.getPermName());
            }
        }
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        // 把Object类型转换为long类型
        long userId = Long.parseLong(loginId.toString());
        long roleId = userMapper.selectById(userId).getRoleId();
        // 根据角色id查询角色名称存入列表
        list.add(roleMapper.selectById(roleId).getRoleName());
        return list;
    }
}

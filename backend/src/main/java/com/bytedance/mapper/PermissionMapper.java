package com.bytedance.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bytedance.model.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}

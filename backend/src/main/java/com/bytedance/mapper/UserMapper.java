package com.bytedance.mapper;

import com.bytedance.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author darling
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2025-01-17 12:33:28
* @Entity com.bytedance.entity.User
*/

@Mapper
public interface UserMapper {

    @Select("select * from douyin_mall.user where username = #{username} and password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}

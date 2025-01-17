package com.bytedance.mapper;

import com.bytedance.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author darling
* @description 针对表【address(收货地址表)】的数据库操作Mapper
* @createDate 2025-01-17 22:30:41
* @Entity com.bytedance.entity.Address
*/

@Mapper
public interface AddressMapper extends BaseMapper<Address> {

}





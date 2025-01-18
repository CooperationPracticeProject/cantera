package com.bytedance.mapper;

import com.bytedance.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author darling
 * @description 针对表【cart(购物车表)】的数据库操作Mapper
 * @createDate 2025-01-17 22:30:41
 * @Entity com.bytedance.entity.Cart
 */

@Mapper
public interface CartMapper extends BaseMapper<Cart> {

}

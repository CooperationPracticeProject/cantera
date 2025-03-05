package com.bytedance.mapper;

import com.bytedance.model.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author darling
 * @description 针对表【order(订单表)】的数据库操作Mapper
 * @createDate 2025-01-17 22:30:41
 * @Entity com.bytedance.entity.Order
 */

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    // 根据订单编号查询订单
    @Select("SELECT * FROM orders WHERE order_no = #{orderNo}")
    Order selectByOrderNo(String orderNo);

    // 根据用户ID查询该用户的所有订单
    @Select("SELECT * FROM orders WHERE user_id = #{userId}")
    List<Order> selectOrdersByUserId(Long userId);
}

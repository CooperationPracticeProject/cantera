package com.bytedance.mapper;

import com.bytedance.model.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author darling
 * @description 针对表【order_item(订单商品表)】的数据库操作Mapper
 * @createDate 2025-01-17 22:30:41
 * @Entity com.bytedance.entity.OrderItem
 */

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    int insertOrderItem(OrderItem orderItem);
    List<OrderItem> findOrderItemsByOrderId(Long orderId);
    int deleteOrderItemsByOrderId(Long orderId);
}

package com.bytedance.service;

import com.bytedance.model.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author darling
 * @description 针对表【order_item(订单商品表)】的数据库操作Service
 * @createDate 2025-01-17 22:30:41
 */
public interface OrderItemService extends IService<OrderItem> {
    OrderItem createOrderItem(Long productId, Integer quantity, Double price);
    OrderItem getOrderItemById(Long orderItemId);
    List<OrderItem> getOrderItemsByOrderId(Long orderId);
    boolean updateOrderItem(Long orderItemId, Integer quantity, Double price);
    boolean deleteOrderItem(Long orderItemId);
}

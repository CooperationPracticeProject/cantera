package com.bytedance.service;

import com.bytedance.model.dto.OrdersSubmitDTO;
import com.bytedance.model.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.model.entity.OrderItem;
import com.bytedance.model.vo.OrderSubmitVo;

import java.util.List;

/**
 * @author darling
 * @description 针对表【order(订单表)】的数据库操作Service
 * @createDate 2025-01-17 22:30:41
 */
public interface OrderService extends IService<Order> {
    Order createOrder(Long userId, List<OrderItem> items);

    Order getOrderDetails(Long orderId);

    boolean updateOrderStatus(Long orderId, String status);

    boolean deleteOrder(Long orderId);
}
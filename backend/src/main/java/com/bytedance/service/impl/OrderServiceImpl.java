package com.bytedance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.model.dto.OrdersSubmitDTO;
import com.bytedance.model.entity.Order;
import com.bytedance.model.vo.OrderSubmitVo;
import com.bytedance.service.OrderService;
import com.bytedance.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author darling
 * @description 针对表【order(订单表)】的数据库操作Service实现
 * @createDate 2025-01-17 22:30:41
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
  implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public OrderSubmitVo submitOrder(OrdersSubmitDTO ordersSubmitDTO) {
        return null;
    }
}

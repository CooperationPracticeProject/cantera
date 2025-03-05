package com.bytedance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.mapper.AddressMapper;
import com.bytedance.mapper.OrderItemMapper;
import com.bytedance.model.dto.OrdersSubmitDTO;
import com.bytedance.model.entity.Address;
import com.bytedance.model.entity.Order;
import com.bytedance.model.entity.OrderItem;
import com.bytedance.model.vo.OrderSubmitVo;
import com.bytedance.service.OrderService;
import com.bytedance.mapper.OrderMapper;
import jakarta.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.List;
import java.util.Date;
/**
 * @author darling
 * @description 针对表【order(订单表)】的数据库操作Service实现
 * @createDate 2025-01-17 22:30:41
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
  implements OrderService {
    @Autowired
    private OrderItemMapper orderItemMapper;  // 用来操作订单项

    @Autowired
    private OrderMapper orderMapper;  // 用来操作订单表

    /**
     * 创建订单
     *
     * @param userId 用户ID
     * @param items 订单项列表
     * @return 新创建的订单
     */
    @Transactional
    @Override
    public Order createOrder(Long userId, List<OrderItem> items) {
        // 创建订单对象
        Order order = new Order();

        // 设置订单的用户ID
        order.setUserId(userId);

        // 设置订单的默认状态为待付款(0)
        order.setStatus(0);  // 0 代表待付款状态

        // 设置订单创建时间为当前时间
        order.setCreatedAt(new Date());  // 当前时间为创建时间

        // 计算订单总金额
        order.setTotalAmount(calculateTotalAmount(items));  // 调用方法计算总金额

        // 插入订单数据到数据库
        boolean result = save(order);  // 使用 MyBatis-Plus 的 save() 方法保存订单

        if (result) {
            // 保存订单项到数据库
            for (OrderItem item : items) {
                item.setOrderId(order.getId());  // 设置订单项的订单ID
                orderItemMapper.insert(item);  // 保存订单项数据
            }
            return order;  // 返回新创建的订单
        } else {
            throw new RuntimeException("订单创建失败");
        }
    }

    /**
     * 获取订单详情
     *
     * @param orderId 订单ID
     * @return 订单详情
     */
    @Override
    public Order getOrderDetails(Long orderId) {
        return orderMapper.selectById(orderId);  // 使用 MyBatis-Plus 的 selectById 查询订单
    }

    /**
     * 更新订单状态
     *
     * @param orderId 订单ID
     * @param status  新的订单状态
     * @return 是否更新成功
     */
    @Override
    public boolean updateOrderStatus(Long orderId, String status) {
        Order order = orderMapper.selectById(orderId);
        if (order != null) {
            order.setStatus(Integer.parseInt(status));  // 设置订单的新状态
            return updateById(order);  // 使用 MyBatis-Plus 的 updateById 更新订单
        }
        return false;
    }

    /**
     * 删除订单
     *
     * @param orderId 订单ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteOrder(Long orderId) {
        // 删除订单及其订单项
        orderItemMapper.deleteOrderItemsByOrderId(orderId);  // 删除订单项
        return removeById(orderId);  // 使用 MyBatis-Plus 的 removeById 删除订单
    }

    // 计算订单总金额
    private Double calculateTotalAmount(List<OrderItem> items) {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getPrice() * item.getQuantity();  // 每个订单项的小计
        }
        return total;  // 返回总金额
    }
}

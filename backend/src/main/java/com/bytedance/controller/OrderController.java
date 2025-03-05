package com.bytedance.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.bytedance.model.entity.Order;
import com.bytedance.model.entity.OrderItem;
import com.bytedance.model.dto.OrdersSubmitDTO;
import com.bytedance.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SaCheckLogin
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param ordersSubmitDTO 订单提交的数据，包括地址ID、优惠券ID、订单Token和备注
     * @param userId 用户ID（假设前端传递或者从Session中获取）
     * @param items 订单项列表（假设前端传递的订单项数据）
     * @return 返回创建的订单
     */
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(
            @RequestBody OrdersSubmitDTO ordersSubmitDTO,
            @RequestParam Long userId,  // 假设用户ID从请求中获取
            @RequestBody List<OrderItem> items  // 假设订单项数据通过请求传递
    ) {
        try {
            // 提取订单相关数据
            Long addressId = ordersSubmitDTO.getAddressId();
            Long couponId = ordersSubmitDTO.getCouponId();
            String orderToken = ordersSubmitDTO.getOrderToken();
            String note = ordersSubmitDTO.getNote();

            // 调用服务层创建订单
            Order createdOrder = orderService.createOrder(userId, items);

            // 返回创建的订单
            return ResponseEntity.ok(createdOrder);
        } catch (Exception e) {
            // 异常处理，返回内部服务器错误
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * 获取订单详情
     * @param orderId 订单ID
     * @return 返回订单详情
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long orderId) {
        try {
            Order order = orderService.getOrderDetails(orderId);
            if (order != null) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.status(404).body(null);  // 订单未找到
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);  // 处理异常
        }
    }

    /**
     * 更新订单状态
     * @param orderId 订单ID
     * @param status 新的状态
     * @return 返回是否更新成功
     */
    @PutMapping("/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        try {
            boolean updated = orderService.updateOrderStatus(orderId, status);
            if (updated) {
                return ResponseEntity.ok("订单状态更新成功");
            } else {
                return ResponseEntity.status(404).body("订单未找到");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("订单状态更新失败");
        }
    }

    /**
     * 删除订单
     * @param orderId 订单ID
     * @return 返回删除操作的结果
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        try {
            boolean deleted = orderService.deleteOrder(orderId);
            if (deleted) {
                return ResponseEntity.ok("订单删除成功");
            } else {
                return ResponseEntity.status(404).body("订单未找到");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("订单删除失败");
        }
    }
}

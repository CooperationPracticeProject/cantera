package com.bytedance.entity;

import lombok.Data;
import java.util.Date;

/**
 * @author: 繁星_逐梦
 * @date: 2025/01/16/ 16:08
 * @description: 订单商品类
 */
@Data
public class OrderItem {
    // 订单商品ID
    private Long id;

    // 订单ID
    private Long orderId;

    // 商品ID
    private Long productId;

    // 商品标题
    private String productTitle;

    // 购买价格
    private Double price;

    // 购买数量
    private Integer quantity;

    // 创建时间
    private Date createdAt;
}
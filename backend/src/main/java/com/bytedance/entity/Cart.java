package com.bytedance.entity;

import lombok.Data;
import java.util.Date;

/**
 * @author: 繁星_逐梦
 * @date: 2025/01/16/ 16:08
 * @description: 购物车类
 */
@Data
public class Cart {
    // 购物车ID
    private Long id;

    // 用户ID
    private Long userId;

    // 商品ID
    private Long productId;

    // 商品数量
    private Integer quantity;

    // 创建时间
    private Date createdAt;

    // 更新时间
    private Date updatedAt;
}
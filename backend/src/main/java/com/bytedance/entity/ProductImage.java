package com.bytedance.entity;

import lombok.Data;

/**
 * @author: 繁星_逐梦
 * @date: 2025/01/16/ 16:08
 * @description: 商品图片类
 */
@Data
public class ProductImage {
    // 图片ID
    private Long id;

    // 商品ID
    private Long productId;

    // 图片URL
    private String imageUrl;
}
package com.bytedance.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/23 下午9:38
 * @description: ProductDto类
 */

@Data
public class ProductDto {
    // 卖家ID
    @TableField("seller_id")
    private Long sellerId;

    // 分类ID
    @TableField ("category_id")
    private Long categoryId;

    // 商品标题
    private String title;

    // 商品描述
    private String description;

    // 售价
    private Double price;

    // 库存
    private Integer stock;

    // 销量
    private Integer sales;

    // 主图URL
    @TableField ("main_image")
    private String mainImage;

    // 状态(0:下架 1:上架)
    private Integer status;
}

package com.bytedance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.util.Date;

/**
 * @author: 繁星_逐梦
 * @date: 2025/01/16/ 16:08
 * @description: 商品类
 */
@Data
public class Product {
    // 商品ID
    private Long id;

    // 卖家ID
    @TableField("seller_id")
    private Long sellerId;

    // 分类ID
    @TableField("category_id")
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
    @TableField("main_image")
    private String mainImage;

    // 状态(0:下架 1:上架)
    private Integer status;

    // 创建时间
    @TableField("created_at")
    private Date createdAt;
}

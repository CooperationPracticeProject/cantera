package com.bytedance.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
  @TableField ("order_id")
  private Long orderId;

  // 商品ID
  @TableField ("product_id")
  private Long productId;

  // 商品标题
  @TableField ("product_title")
  private String productTitle;

  // 购买价格
  private Double price;

  // 购买数量
  private Integer quantity;

  // 创建时间
  @TableField ("created_at")
  private Date createdAt;

}

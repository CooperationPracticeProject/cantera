package com.bytedance.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.util.Date;

/**
 * @author: 繁星_逐梦
 * @date: 2025/01/16/ 16:08
 * @description: 订单类
 */
@Data
public class Order {

  // 订单ID
  private Long id;

  // 订单编号
  @TableField ("order_no")
  private String orderNo;

  // 用户ID
  @TableField ("user_id")
  private Long userId;

  // 卖家ID
  @TableField ("seller_id")
  private Long sellerId;

  // 订单总金额
  @TableField ("total_amount")
  private Double totalAmount;

  // 订单状态(0:待付款 1:待发货 2:待收货 3:已完成 4:已取消)
  private Integer status;

  // 收货地址ID
  @TableField ("address_id")
  private Long addressId;

  // 创建时间
  @TableField ("created_at")
  private Date createdAt;

}

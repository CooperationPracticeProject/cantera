package com.bytedance.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author: 繁星_逐梦
 * @date: 2025/2/7 下午2:09
 * @description: CartItem类
 */
@Data
@TableName("cart_item")
public class CartItem {

    // 购物项ID
    @TableId
    private Long id;

    @TableField("user_id")
    private Long userId;  // 绑定用户ID

    @TableField("product_id")
    private Long productId;  // 商品ID

    @TableField("quantity")
    private Integer quantity;  // 商品数量

    @TableField("updated_at")
    private Date updatedAt;  // 更新时间
}

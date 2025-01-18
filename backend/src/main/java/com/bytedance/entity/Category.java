package com.bytedance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author: 繁星_逐梦
 * @date: 2025/01/16/ 16:08
 * @description: 商品分类类
 */
@Data
public class Category {

  // 分类ID
  private Long id;

  // 分类名称
  private String name;

  // 父分类ID
  @TableField ("parent_id")
  private Long parentId;

  // 状态(0:禁用 1:启用)
  private Integer status;

}

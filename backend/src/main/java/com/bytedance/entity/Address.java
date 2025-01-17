package com.bytedance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author: 繁星_逐梦
 * @date: 2025/01/16/ 16:08
 * @description: 收货地址类
 */
@Data
public class Address {
    // 地址ID
    private Long id;

    // 用户ID
    @TableField("user_id")
    private Long userId;

    // 收货人姓名
    @TableField("receiver_name")
    private String receiverName;

    // 收货人电话
    @TableField("receiver_phone")
    private String receiverPhone;

    // 省
    @TableField("receiver_province")
    private String receiverProvince;

    // 市
    @TableField("receiver_city")
    private String receiverCity;

    // 区
    @TableField("receiver_district")
    private String receiverDistrict;

    // 详细地址
    @TableField("receiver_detail_address")
    private String receiverDetailAddress;

    // 是否默认(0:否 1:是)
    @TableField("is_default")
    private Integer isDefault;
}
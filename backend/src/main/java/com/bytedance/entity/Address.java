package com.bytedance.entity;

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
    private Long userId;

    // 收货人姓名
    private String receiverName;

    // 收货人电话
    private String receiverPhone;

    // 省
    private String receiverProvince;

    // 市
    private String receiverCity;

    // 区
    private String receiverDistrict;

    // 详细地址
    private String receiverDetailAddress;

    // 是否默认(0:否 1:是)
    private Integer isDefault;
}
package com.bytedance.model.query;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/21 下午8:18
 * @description: 商品查询条件实体类，用于服务层筛选查询
 */


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuery {
    private Long id; // 商品ID，用于精确查询
    private Long sellerId; // 卖家ID，用于查询某个卖家的商品
    private Long categoryId; // 分类ID，用于查询某个分类下的商品
    private String title; // 商品标题，支持模糊查询
    private BigDecimal minPrice; // 最低售价，用于价格区间查询
    private BigDecimal maxPrice; // 最高售价，用于价格区间查询
    private Integer minStock; // 最小库存，用于库存区间查询
    private Integer maxStock; // 最大库存，用于库存区间查询
    private Integer minSales; // 最小销量，用于销量区间查询
    private Integer maxSales; // 最大销量，用于销量区间查询
    private Integer status; // 商品状态（0: 下架, 1: 上架）

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 指定日期格式
    private Date createdAtStart; // 创建时间起始，用于时间区间查询

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 指定日期格式
    private Date createdAtEnd; // 创建时间结束，用于时间区间查询

    private String sortField; // 排序字段，例如："price", "sales", "createdAt"
    private Boolean sortAsc; // 排序顺序，true: 升序（asc），false: 降序（desc）
    private Integer page = 1; // 当前页码，默认第一页
    private Integer size = 20; // 每页条数，默认20条
}

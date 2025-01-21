package com.bytedance.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bytedance.model.entity.Product;
import com.bytedance.model.query.ProductQuery;
import com.bytedance.service.ProductService;
import com.bytedance.util.Result;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/21 下午7:25
 * @description: ProductController类
 */

@RestController
@RequestMapping("/products")
public class ProductController {

    @Resource
    ProductService productService;

    @GetMapping("/list")
    public Result<List<Product>> listByPage(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize
    ){

        // 调用 service 层的分页查询方法
        IPage<Product> productPage = productService.listByPage(pageNo, pageSize);

        List<Product> products = productPage.getRecords();

        // 返回分页结果
        return Result.of(Result.ResultCode.SUCCESS, products);
    }


    @GetMapping("/{id}")
    public Result<Product> getProductById(@PathVariable("id") Integer id){
        Product product = productService.getById(id);
        if (Objects.isNull(product)) return Result.of(Result.ResultCode.NOT_FOUND, null);
        return Result.of(Result.ResultCode.SUCCESS, product);
    }


    /**
     * 查询商品列表
     *
     * @param id            商品ID
     * @param sellerId      卖家ID
     * @param categoryId    分类ID
     * @param title         商品标题
     * @param minPrice      最低售价
     * @param maxPrice      最高售价
     * @param minStock      最小库存
     * @param maxStock      最大库存
     * @param minSales      最小销量
     * @param maxSales      最大销量
     * @param status        商品状态
     * @param createdAtStart 创建时间起始
     * @param createdAtEnd   创建时间结束
     * @param sortField      排序字段
     * @param sortAsc        排序顺序（true: 升序, false: 降序）
     * @param page           当前页数(默认第一页)
     * @param size           分页每页数量(默认20)
     * @return 商品列表
     */
    @GetMapping("/query")
    public List<Product> queryProducts(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "sellerId", required = false) Long sellerId,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "minStock", required = false) Integer minStock,
            @RequestParam(value = "maxStock", required = false) Integer maxStock,
            @RequestParam(value = "minSales", required = false) Integer minSales,
            @RequestParam(value = "maxSales", required = false) Integer maxSales,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "createdAtStart", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date createdAtStart,
            @RequestParam(value = "createdAtEnd", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date createdAtEnd,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "sortAsc", required = false) Boolean sortAsc,
            @RequestParam(value = "page", defaultValue = "1") Integer page,  // 默认第一页
            @RequestParam(value = "size", defaultValue = "20") Integer size) { // 默认每页20条

        // 构建查询条件
        ProductQuery productQuery = new ProductQuery(
                id, sellerId, categoryId, title, minPrice, maxPrice,
                minStock, maxStock, minSales, maxSales, status,
                createdAtStart, createdAtEnd, sortField, sortAsc, page, size
        );

        // 调用服务层方法
        return productService.queryProducts(productQuery);
    }

}

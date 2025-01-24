package com.bytedance.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bytedance.model.dto.ProductDto;
import com.bytedance.model.entity.Product;
import com.bytedance.model.query.ProductQuery;
import com.bytedance.service.ProductService;
import com.bytedance.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
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
     * @param productQuery 查询条件对象
     * @return 商品列表
     */
    @GetMapping("/query")
    public List<Product> queryProducts(@ModelAttribute ProductQuery productQuery) {
        // 调用服务层方法
        return productService.queryProducts(productQuery);
    }

    @PostMapping("/save")
    public Result<Product> saveProduct(@RequestBody ProductDto productdto){
        //dto转实体
        Product product = BeanUtil.toBean(productdto, Product.class);
        product.setCreatedAt(new java.util.Date());//设置创建时间当前时间
        boolean isSuccess = productService.save(product);
        if (!isSuccess) return Result.of(Result.ResultCode.FAIL, null);
        return Result.of(Result.ResultCode.SUCCESS, product);
    }

    @PutMapping("/update")
    public Result<Product> updateProduct(@RequestBody ProductDto productdto){
        //dto转实体
//        Product product = BeanUtil.toBean(productdto, Product.class);
//        boolean isSuccess = productService.updateById(product);
//        if (!isSuccess) return Result.of(Result.ResultCode.FAIL, null);
//        return Result.of(Result.ResultCode.SUCCESS, product);
        return Result.of(Result.ResultCode.SUCCESS, null);
    }


    @DeleteMapping("/delete/{id}")
    public Result<Product> deleteProduct(@PathVariable("id") Integer id){
        //查询id商品是否存在
        Product product = productService.getById(id);
        if (Objects.isNull(product)) return Result.of(Result.ResultCode.NOT_FOUND, null);
        boolean isSuccess = productService.removeById(id);
        if (!isSuccess) return Result.of(Result.ResultCode.FAIL, null);
        return Result.of(Result.ResultCode.SUCCESS, null);
    }

}

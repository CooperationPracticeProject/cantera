package com.bytedance.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bytedance.model.entity.Product;
import com.bytedance.model.query.ProductQuery;
import com.bytedance.service.ProductService;
import com.bytedance.util.Result;
import com.bytedance.util.FileUpload;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.web.multipart.MultipartFile;

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

    @Resource
    FileUpload imageUploadService;

    @GetMapping("/list")
    public Result<IPage<Product>> listByPage(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize
    ) {
        IPage<Product> productPage = productService.listByPage(pageNo, pageSize);
        return Result.of(Result.ResultCode.SUCCESS, productPage);
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

    @PostMapping("/create")
    public Result<Product> saveProduct(@RequestParam(value = "image", required = false) MultipartFile image,
                                       @RequestParam(value = "product") String productJson){
        // 处理 JSON 字符串
        Product product = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            product = objectMapper.readValue(productJson, Product.class);  // 将 JSON 字符串转换为 Product 对象
        } catch (Exception e) {
            return Result.of(Result.ResultCode.INVALID_PARAM, null);
        }

        // 处理图片上传
        if (image != null && !image.isEmpty()) {
            // 调用上传工具类上传图片，返回图片 URL
            Result<String> uploadResult = imageUploadService.upload(image);
            product.setMainImage(uploadResult.getData());  // 设置图片 URL
        }

        System.out.println("接收到的product:"+product.toString());

        product.setId(null); // 将 id 清空，确保自动生成
        product.setCreatedAt(new Date());// 在创建时忽略前端传递的createTime，后端自动生成(防止前端伪造创建时间)

        // 如果价格或库存无效，返回错误
        if (product.getPrice() <= 0 || product.getStock() < 0) {
            return Result.of(Result.ResultCode.INVALID_PARAM, null);
        }

        // 保存商品信息
        boolean isSuccess = productService.save(product);
        if (!isSuccess) return Result.of(Result.ResultCode.FAIL, null);
        return Result.of(Result.ResultCode.SUCCESS, product);
    }

    @PostMapping("/update")
    public Result<Product> updateProduct(@RequestParam(value = "image", required = false) MultipartFile image,
                                         @RequestParam(value = "product") String productJson){
        // 处理 JSON 字符串
        Product product;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            product = objectMapper.readValue(productJson, Product.class);  // 将 JSON 字符串转换为 Product 对象
        } catch (Exception e) {
            return Result.of(Result.ResultCode.INVALID_PARAM, null);
        }
        // 如果前端上传了新的图片，处理图片上传
        if (image != null && !image.isEmpty()) {
            // 调用上传工具类上传图片，返回图片 URL
            Result<String> uploadResult = imageUploadService.upload(image);
            product.setMainImage(uploadResult.getData());  // 设置新的图片 URL
        }else {
            product.setMainImage(null);
        }

        // 如果价格或库存无效，返回错误
        if (product.getPrice() <= 0 || product.getStock() < 0) {
            return Result.of(Result.ResultCode.INVALID_PARAM, null);
        }

        // 在更新时忽略 createTime，避免前端修改它
        product.setCreatedAt(null);

        // 更新商品信息
        boolean isSuccess = productService.updateById(product);
        if (!isSuccess) return Result.of(Result.ResultCode.FAIL, null);
        return Result.of(Result.ResultCode.SUCCESS, product);
    }

    @DeleteMapping("/delete/{id}")
    public Result<Product> deleteProduct(@PathVariable("id") Integer id){
        boolean isSuccess = productService.removeById(id);
        if (!isSuccess) return Result.of(Result.ResultCode.NOT_FOUND, null);
        return Result.of(Result.ResultCode.SUCCESS, null);
    }

    //多选删除
    @DeleteMapping("/delete/batch")
    public Result<Product> deleteProducts(@RequestBody List<Integer> ids){
        if (ids == null || ids.isEmpty()) {
            return Result.of(Result.ResultCode.INVALID_PARAM, null);
        }
        boolean isSuccess = productService.removeByIds(ids);
        if (!isSuccess) return Result.of(Result.ResultCode.NOT_FOUND, null);
        return Result.of(Result.ResultCode.SUCCESS, null);
    }

}

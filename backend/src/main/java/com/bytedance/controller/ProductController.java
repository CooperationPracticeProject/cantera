package com.bytedance.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bytedance.model.entity.Product;
import com.bytedance.model.query.ProductQuery;
import com.bytedance.service.ProductService;
import com.bytedance.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Resource
    ProductService productService;

    /**
     * 分页查询商品列表
     *
     * @param pageNo   页码，默认值为1
     * @param pageSize 每页显示的商品数量，默认值为100
     * @return 分页后的商品列表
     */
    @GetMapping("/list")
    public Result<IPage<Product>> listByPage(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize
    ) {
        IPage<Product> productPage = productService.listByPage(pageNo, pageSize);
        return Result.of(Result.ResultCode.SUCCESS, productPage);
    }

    /**
     * 根据ID查询商品信息
     *
     * @param id 商品ID
     * @return 查询到的商品信息，如果未找到则返回NOT_FOUND状态码
     */
    @GetMapping("/{id}")
    public Result<Product> getProductById(@PathVariable("id") Integer id){
        Product product = productService.getById(id);
        if (Objects.isNull(product)) return Result.of(Result.ResultCode.NOT_FOUND, null);
        return Result.of(Result.ResultCode.SUCCESS, product);
    }

    /**
     * 根据查询条件查询商品列表
     *
     * @param productQuery 查询条件对象
     * @return 符合查询条件的商品列表
     */
    @GetMapping("/query")
    public List<Product> queryProducts(@ModelAttribute ProductQuery productQuery) {
        // 调用服务层方法
        return productService.queryProducts(productQuery);
    }

    /**
     * 创建商品
     *
     * @param image     商品图片文件，可选
     * @param productJson 商品信息的JSON字符串
     * @return 创建后的商品信息
     */
    @PostMapping("/create")
    public Result<Product> saveProduct(@RequestParam(value = "image", required = false) MultipartFile image,
                                       @RequestParam(value = "product") String productJson){
        return productService.saveProduct(image, productJson);
    }

    /**
     * 更新商品信息
     *
     * @param image     商品图片文件，可选
     * @param productJson 商品信息的JSON字符串
     * @return 更新后的商品信息
     */
    @PostMapping("/update")
    public Result<Product> updateProduct(@RequestParam(value = "image", required = false) MultipartFile image,
                                         @RequestParam(value = "product") String productJson){
        return productService.updateProduct(image, productJson);
    }

    /**
     * 根据ID删除商品
     *
     * @param id 商品ID
     * @return 删除结果，如果未找到商品则返回NOT_FOUND状态码
     */
    @DeleteMapping("/delete/{id}")
    public Result<Product> deleteProduct(@PathVariable("id") Integer id){
        boolean isSuccess = productService.removeById(id);
        if (!isSuccess) return Result.of(Result.ResultCode.NOT_FOUND, null);
        return Result.of(Result.ResultCode.SUCCESS, null);
    }

    //多选删除
    /**
     * 批量删除商品
     *
     * @param ids 要删除的商品ID列表
     * @return 删除结果，如果ID列表为空或未找到商品则返回相应的状态码
     */
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

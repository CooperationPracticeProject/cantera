package com.bytedance.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.model.entity.Product;
import com.bytedance.model.query.ProductQuery;
import com.bytedance.util.Result;

/**
 * @author darling
 * @description 针对表【product(商品表)】的数据库操作Service
 * @createDate 2025-01-17 22:30:41
 */
public interface ProductService extends IService<Product> {

    IPage<Product> listByPage(Integer pageNo, Integer pageSize);

    List<Product> queryProducts(ProductQuery productQuery);

    Result<Product> saveProduct(MultipartFile image,String productJson);

    Result<Product> updateProduct(MultipartFile image,String productJson);
}

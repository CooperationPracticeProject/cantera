package com.bytedance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bytedance.model.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.model.query.ProductQuery;
import com.bytedance.util.Result;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

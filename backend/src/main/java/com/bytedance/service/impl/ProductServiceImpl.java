package com.bytedance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.entity.Product;
import com.bytedance.service.ProductService;
import com.bytedance.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author darling
* @description 针对表【product(商品表)】的数据库操作Service实现
* @createDate 2025-01-17 22:30:41
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}





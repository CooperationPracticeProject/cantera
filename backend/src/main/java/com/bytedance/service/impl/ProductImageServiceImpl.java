package com.bytedance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.model.entity.ProductImage;
import com.bytedance.service.ProductImageService;
import com.bytedance.mapper.ProductImageMapper;
import org.springframework.stereotype.Service;

/**
 * @author darling
 * @description 针对表【product_image(商品图片表)】的数据库操作Service实现
 * @createDate 2025-01-17 22:30:41
 */
@Service
public class ProductImageServiceImpl extends ServiceImpl<ProductImageMapper, ProductImage>
  implements ProductImageService {

}

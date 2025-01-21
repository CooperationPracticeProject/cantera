package com.bytedance.mapper;

import com.bytedance.model.entity.ProductImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author darling
 * @description 针对表【product_image(商品图片表)】的数据库操作Mapper
 * @createDate 2025-01-17 22:30:41
 * @Entity com.bytedance.entity.ProductImage
 */

@Mapper
public interface ProductImageMapper extends BaseMapper<ProductImage> {

}

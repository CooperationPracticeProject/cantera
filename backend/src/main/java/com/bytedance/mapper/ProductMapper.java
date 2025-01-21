package com.bytedance.mapper;

import com.bytedance.model.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author darling
 * @description 针对表【product(商品表)】的数据库操作Mapper
 * @createDate 2025-01-17 22:30:41
 * @Entity com.bytedance.entity.Product
 */

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}

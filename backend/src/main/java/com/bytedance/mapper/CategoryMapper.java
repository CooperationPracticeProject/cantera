package com.bytedance.mapper;

import com.bytedance.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author darling
 * @description 针对表【category(商品分类表)】的数据库操作Mapper
 * @createDate 2025-01-17 22:30:41
 * @Entity com.bytedance.entity.Category
 */

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}

package com.bytedance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.entity.Category;
import com.bytedance.service.CategoryService;
import com.bytedance.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author darling
* @description 针对表【category(商品分类表)】的数据库操作Service实现
* @createDate 2025-01-17 22:30:41
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}





package com.bytedance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.model.entity.Product;
import com.bytedance.model.query.ProductQuery;
import com.bytedance.service.ProductService;
import com.bytedance.mapper.ProductMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author darling
 * @description 针对表【product(商品表)】的数据库操作Service实现
 * @createDate 2025-01-17 22:30:41
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
  implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public IPage<Product> listByPage(Integer pageNo, Integer pageSize) {
        // 创建 Page 对象，传入当前页和每页条数
        Page<Product> pageRequest = new Page<>(pageNo, pageSize);

        // 使用 MyBatis-Plus 分页查询方法
        return this.page(pageRequest);
    }

    @Override
    public List<Product> queryProducts(ProductQuery productQuery) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();

        // 精确匹配商品ID
        queryWrapper.eq(productQuery.getId() != null, Product::getId, productQuery.getId());
        // 精确匹配卖家ID
        queryWrapper.eq(productQuery.getSellerId() != null, Product::getSellerId, productQuery.getSellerId());
        // 精确匹配分类ID
        queryWrapper.eq(productQuery.getCategoryId() != null, Product::getCategoryId, productQuery.getCategoryId());
        // 模糊匹配商品标题
        queryWrapper.like(StringUtils.isNotBlank(productQuery.getTitle()), Product::getTitle, productQuery.getTitle());
        // 价格区间查询
        queryWrapper.ge(productQuery.getMinPrice() != null, Product::getPrice, productQuery.getMinPrice());
        queryWrapper.le(productQuery.getMaxPrice() != null, Product::getPrice, productQuery.getMaxPrice());
        // 库存区间查询
        queryWrapper.ge(productQuery.getMinStock() != null, Product::getStock, productQuery.getMinStock());
        queryWrapper.le(productQuery.getMaxStock() != null, Product::getStock, productQuery.getMaxStock());
        // 销量区间查询
        queryWrapper.ge(productQuery.getMinSales() != null, Product::getSales, productQuery.getMinSales());
        queryWrapper.le(productQuery.getMaxSales() != null, Product::getSales, productQuery.getMaxSales());
        // 精确匹配商品状态
        queryWrapper.eq(productQuery.getStatus() != null, Product::getStatus, productQuery.getStatus());
        // 创建时间区间查询
        queryWrapper.ge(productQuery.getCreatedAtStart() != null, Product::getCreatedAt, productQuery.getCreatedAtStart());
        queryWrapper.le(productQuery.getCreatedAtEnd() != null, Product::getCreatedAt, productQuery.getCreatedAtEnd());

        // 排序逻辑
        if (StringUtils.isNotBlank(productQuery.getSortField())) {
            String sortField = productQuery.getSortField();
            boolean isAsc = productQuery.getSortAsc() != null ? productQuery.getSortAsc() : true; // 默认升序
            switch (sortField) {
                case "price":
                    queryWrapper.orderBy(true, isAsc, Product::getPrice);
                    break;
                case "sales":
                    queryWrapper.orderBy(true, isAsc, Product::getSales);
                    break;
                case "createdAt":
                    queryWrapper.orderBy(true, isAsc, Product::getCreatedAt);
                    break;
                default:
                    // 默认按创建时间降序
                    queryWrapper.orderBy(true, false, Product::getCreatedAt);
                    break;
            }
        } else {
            // 默认按创建时间降序
            queryWrapper.orderBy(true, false, Product::getCreatedAt);
        }

        // 分页查询
        Page<Product> pageRequest = new Page<>(productQuery.getPage(), productQuery.getSize());
        return productMapper.selectPage(pageRequest, queryWrapper).getRecords(); // 返回当前页的数据列表
    }

}

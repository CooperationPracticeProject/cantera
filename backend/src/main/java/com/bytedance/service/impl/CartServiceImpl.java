package com.bytedance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.entity.Cart;
import com.bytedance.service.CartService;
import com.bytedance.mapper.CartMapper;
import org.springframework.stereotype.Service;

/**
* @author darling
* @description 针对表【cart(购物车表)】的数据库操作Service实现
* @createDate 2025-01-17 22:30:41
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
    implements CartService{

}





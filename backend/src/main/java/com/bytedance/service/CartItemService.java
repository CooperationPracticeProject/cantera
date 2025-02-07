package com.bytedance.service;

import com.bytedance.model.entity.CartItem;
import java.util.List;

public interface CartItemService {

    // 添加购物车商品
    CartItem addCartItem(Long userId, Long productId, Integer quantity);

    // 获取购物车商品列表
    List<CartItem> getCartItems(Long userId);

    // 更新购物车商品数量
    CartItem updateCartItem(Long userId, Long productId, Integer quantity);

    // 删除购物车中的商品
    void removeCartItem(Long userId, Long productId);

    // 清空购物车
    void clearCart(Long userId);
}

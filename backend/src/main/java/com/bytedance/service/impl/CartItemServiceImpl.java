package com.bytedance.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.mapper.CartItemMapper;
import com.bytedance.model.entity.CartItem;
import com.bytedance.service.CartItemService;

import jakarta.annotation.Resource;

/**
 * @author: 繁星_逐梦
 * @date: 2025/2/7 下午2:17
 * @description: CartItemServiceImpl类
 */

@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem>
        implements CartItemService {

    @Resource
    private CartItemMapper cartItemMapper;

    @Override
    public CartItem addCartItem(Long userId, Long productId, Integer quantity) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("product_id", productId);
        CartItem existingItem = cartItemMapper.selectOne(queryWrapper);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setUpdatedAt(new Date());
            cartItemMapper.updateById(existingItem);
            return existingItem;
        } else {
            CartItem newItem = new CartItem();
            newItem.setUserId(userId);
            newItem.setProductId(productId);
            newItem.setQuantity(quantity);
            newItem.setUpdatedAt(new Date());
            cartItemMapper.insert(newItem);
            return newItem;
        }
    }

    @Override
    public List<CartItem> getCartItems(Long userId) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return cartItemMapper.selectList(queryWrapper);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long productId, Integer quantity) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("product_id", productId);
        CartItem cartItem = cartItemMapper.selectOne(queryWrapper);

        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            cartItem.setUpdatedAt(new Date());
            cartItemMapper.updateById(cartItem);
        }
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long productId) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("product_id", productId);
        cartItemMapper.delete(queryWrapper);
    }

    @Override
    public void clearCart(Long userId) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        cartItemMapper.delete(queryWrapper);
    }
}

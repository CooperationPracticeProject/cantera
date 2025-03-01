package com.bytedance.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.bytedance.model.entity.CartItem;
import com.bytedance.service.CartItemService;
import com.bytedance.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SaCheckLogin
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartItemService cartService;


    // 获取购物车商品列表
    @GetMapping("/list")
    public Result<List<CartItem>> getCartItems(@RequestParam Long userId) {
        List<CartItem> cartItems = cartService.getCartItems(userId);
        return Result.of(Result.ResultCode.SUCCESS, cartItems);
    }

    /**
     * 添加商品到购物车
     * @param userId    用户ID
     * @param productId 商品ID
     * @param quantity  商品数量
     * @return
     */
    @PostMapping("/add")
    public Result<CartItem> addToCart(@RequestParam("userId") Long userId,
                            @RequestParam("productId") Long productId,
                            @RequestParam("quantity") Integer quantity) {
        CartItem cartItem = cartService.addCartItem(userId, productId, quantity);
        return Result.of(Result.ResultCode.SUCCESS, cartItem);
    }

    // 更新购物车商品数量
    @PostMapping("/update")
    public Result<CartItem> updateCartItem(@RequestParam Long userId, @RequestParam Long productId, @RequestParam Integer quantity) {
        CartItem cartItem = cartService.updateCartItem(userId, productId, quantity);
        return Result.of(Result.ResultCode.SUCCESS, cartItem);
    }

    // 删除购物车商品
    @PostMapping("/remove")
    public Result<CartItem> removeCartItem(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.removeCartItem(userId, productId);
        return Result.of(Result.ResultCode.SUCCESS, null);
    }

    // 清空购物车
    @PostMapping("/clear")
    public Result<CartItem> clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
        return Result.of(Result.ResultCode.SUCCESS, null);
    }
}

package com.bytedance.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.bytedance.model.entity.Address;
import com.bytedance.service.AddressService;
import com.bytedance.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author: 繁星_逐梦
 * @date: 2025/2/5 下午12:58
 * @description: AddressController类，负责处理收货地址相关的请求
 */
@RestController
@SaCheckLogin
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    /**
     * 获取所有收货地址（分页）
     */
    @GetMapping("/list")
    public Result<List<Address>> getAllAddresses(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                                 @RequestParam( name = "pageSize", defaultValue = "10") int pageSize) {
        return addressService.getAllAddresses(pageNum, pageSize);
    }

    /**
     * 根据用户 ID 查询地址
     */
    @GetMapping("/user/{userId}")
    public Result<List<Address>> getAddressesByUserId(@PathVariable("userId") Long userId) {
        System.out.println("id="+userId);
        return addressService.getAddressesByUserId(userId);
    }

    /**
     * 添加新地址
     */
    @PostMapping("/add")
    public Result<Address> addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    /**
     * 更新地址
     */
    @PutMapping("/update")
    public Result<Address> updateAddress(@RequestBody Address address) {
        return addressService.updateAddress(address);
    }

    /**
     * 设置默认地址
     */
    @PutMapping("/set-default/{id}")
    public Result<Address> setDefaultAddress(@PathVariable("id") Long id) {
        return addressService.setDefaultAddress(id);
    }

    /**
     * 删除地址
     */
    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteAddress(@PathVariable("id") Long id) {
        return addressService.deleteAddress(id);
    }

    /**
     * 批量删除地址
     */
    @DeleteMapping("/delete/batch")
    public Result<List<Long>> deleteAddresses(@RequestBody List<Long> ids) {
        return addressService.deleteAddresses(ids);
    }
}


package com.bytedance.service;

import com.bytedance.model.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.util.Result;

import java.util.List;

/**
 * @author darling
 * @description 针对表【address(收货地址表)】的数据库操作Service
 * @createDate 2025-01-17 22:30:41
 */
public interface AddressService extends IService<Address> {

    Result<List<Address>> getAllAddresses(int pageNum, int pageSize);

    Result<List<Address>> getAddressesByUserId(Long userId);

    Result<Address> addAddress(Address address);

    Result<Address> updateAddress(Address address);

    Result<Address> setDefaultAddress(Long id);

    Result<Void> deleteAddress(Long id);

    Result<List<Long>> deleteAddresses(List<Long> ids);
}


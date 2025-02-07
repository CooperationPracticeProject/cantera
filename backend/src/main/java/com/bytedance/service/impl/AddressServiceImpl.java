package com.bytedance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.model.entity.Address;
import com.bytedance.service.AddressService;
import com.bytedance.mapper.AddressMapper;
import com.bytedance.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author darling
 * @description 针对表【address(收货地址表)】的数据库操作Service实现
 * @createDate 2025-01-17 22:30:41
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
        implements AddressService {

    @Override
    public Result<List<Address>> getAllAddresses(int pageNum, int pageSize) {
        Page<Address> page = new Page<>(pageNum, pageSize);
        IPage<Address> addressPage = this.page(page);
        return Result.of(Result.ResultCode.SUCCESS, addressPage.getRecords());
    }

    @Override
    public Result<List<Address>> getAddressesByUserId(Long userId) {
        List<Address> addressList = this.list(new QueryWrapper<Address>().eq("user_id", userId));
        return Result.of(Result.ResultCode.SUCCESS, addressList);
    }

    @Override
    public Result<Address> addAddress(Address address) {
        if (ObjectUtils.isEmpty(address.getUserId())) {
            return Result.of(Result.ResultCode.PARAM_NOT_COMPLETE, null);
        }

        boolean saved = this.save(address);
        return saved ? Result.of(Result.ResultCode.SUCCESS, address) : Result.of(Result.ResultCode.FAILED, null);
    }

    @Override
    public Result<Address> updateAddress(Address address) {
        if (ObjectUtils.isEmpty(address.getId())) {
            return Result.of(Result.ResultCode.PARAM_NOT_COMPLETE, null);
        }

        Address existingAddress = this.getById(address.getId());
        if (ObjectUtils.isEmpty(existingAddress)) {
            return Result.of(Result.ResultCode.NOT_FOUND, null);
        }

        boolean updated = this.updateById(address);
        return updated ? Result.of(Result.ResultCode.SUCCESS, address) : Result.of(Result.ResultCode.FAILED, null);
    }

    @Override
    @Transactional
    public Result<Address> setDefaultAddress(Long id) {
        Address address = this.getById(id);
        if (ObjectUtils.isEmpty(address)) {
            return Result.of(Result.ResultCode.NOT_FOUND, null);
        }

        // 取消该用户的所有默认地址
        this.update(new UpdateWrapper<Address>()
                .eq("user_id", address.getUserId())
                .set("is_default", 0));

        // 设定当前地址为默认
        address.setIsDefault(1);
        boolean updated = this.updateById(address);

        return updated ? Result.of(Result.ResultCode.SUCCESS, address) : Result.of(Result.ResultCode.FAILED, null);
    }

    @Override
    public Result<Void> deleteAddress(Long id) {
        boolean removed = this.removeById(id);
        return removed ? Result.of(Result.ResultCode.SUCCESS, null) : Result.of(Result.ResultCode.FAILED, null);
    }

    @Override
    @Transactional
    public Result<List<Long>> deleteAddresses(List<Long> ids) {
        List<Long> failedIds = new ArrayList<>();
        for (Long id : ids) {
            if (!this.removeById(id)) {
                failedIds.add(id);
            }
        }
        return failedIds.isEmpty() ? Result.of(Result.ResultCode.SUCCESS, null) : Result.of(Result.ResultCode.FAILED, failedIds);
    }
}


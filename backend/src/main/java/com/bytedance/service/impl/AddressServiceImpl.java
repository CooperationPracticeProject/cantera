package com.bytedance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.model.entity.Address;
import com.bytedance.service.AddressService;
import com.bytedance.mapper.AddressMapper;
import org.springframework.stereotype.Service;

/**
 * @author darling
 * @description 针对表【address(收货地址表)】的数据库操作Service实现
 * @createDate 2025-01-17 22:30:41
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
  implements AddressService {

}

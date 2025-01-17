package com.bytedance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.entity.OrderItem;
import com.bytedance.service.OrderItemService;
import com.bytedance.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author darling
* @description 针对表【order_item(订单商品表)】的数据库操作Service实现
* @createDate 2025-01-17 22:30:41
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}





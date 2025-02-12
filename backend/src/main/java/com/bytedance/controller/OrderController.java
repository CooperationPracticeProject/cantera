package com.bytedance.controller;

import com.bytedance.model.dto.OrdersSubmitDTO;
import com.bytedance.model.vo.OrderSubmitVo;
import com.bytedance.service.OrderService;
import com.bytedance.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 15827
 * @date: 2025/2/10 15:33
 * @description: OrderController类
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public Result<OrderSubmitVo> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        OrderSubmitVo orderSubmitVo =  orderService.submitOrder(ordersSubmitDTO);
        return Result.success("success", orderSubmitVo);    }
}

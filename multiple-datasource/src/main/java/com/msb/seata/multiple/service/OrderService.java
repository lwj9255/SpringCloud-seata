package com.msb.seata.multiple.service;


import com.msb.seata.multiple.entity.Order;
import com.msb.seata.multiple.vo.OrderVo;

public interface OrderService {

    /**
     * 保存订单
     */
    Order saveOrder(OrderVo orderVo);
}
package com.nx.seata.multiple.service;


import com.nx.seata.multiple.entity.Order;
import com.nx.seata.multiple.vo.OrderVo;

public interface OrderService {

    /**
     * 保存订单
     */
    Order saveOrder(OrderVo orderVo);
}
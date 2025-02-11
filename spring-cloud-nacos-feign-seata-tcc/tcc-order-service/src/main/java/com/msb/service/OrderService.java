package com.msb.service;
import com.msb.database.tcc.config.entity.Order;
import com.msb.vo.OrderVo;

public interface OrderService {

    Order saveOrder(OrderVo orderVo);

}
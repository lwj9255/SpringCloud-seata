package com.nx.seata.multiple.service.impl;


import com.nx.seata.multiple.annotation.DataSource;


import com.nx.seata.multiple.config.DataSourceKey;
import com.nx.seata.multiple.config.DynamicDataSourceContextHolder;
import com.nx.seata.multiple.entity.Order;
import com.nx.seata.multiple.entity.OrderStatus;
import com.nx.seata.multiple.mapper.OrderMapper;
import com.nx.seata.multiple.service.AccountService;
import com.nx.seata.multiple.service.OrderService;
import com.nx.seata.multiple.service.StorageService;
import com.nx.seata.multiple.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.nx.seata.multiple.config.DataSourceKey.ORDER;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private StorageService storageService;
    
    @Override
    @DataSource(ORDER)
    @Transactional
    //@GlobalTransactional(name="createOrder")
    public Order saveOrder(OrderVo orderVo){
        log.info("=============用户下单=================");
        //切换数据源
     //   log.info("当前 XID: {}", RootContext.getXID());
        
        // 保存订单
        Order order = new Order();
        order.setUserId(orderVo.getUserId());
        order.setCommodityCode(orderVo.getCommodityCode());
        order.setCount(orderVo.getCount());
        order.setMoney(orderVo.getMoney());
        order.setStatus(OrderStatus.INIT.getValue());
    
        Integer saveOrderRecord = orderMapper.insert(order);
        log.info("保存订单{}", saveOrderRecord > 0 ? "成功" : "失败");
        
        //扣减库存
        storageService.deduct(orderVo.getCommodityCode(),orderVo.getCount());
        
        //扣减余额
        accountService.debit(orderVo.getUserId(),orderVo.getMoney());
    
        log.info("=============更新订单状态=================");
        //更新订单
        Integer updateOrderRecord = orderMapper.updateOrderStatus(order.getId(),OrderStatus.SUCCESS.getValue());
        log.info("更新订单id:{} {}", order.getId(), updateOrderRecord > 0 ? "成功" : "失败");
        
        return order;
        
    }
}

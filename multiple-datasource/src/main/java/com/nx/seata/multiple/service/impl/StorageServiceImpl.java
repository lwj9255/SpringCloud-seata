package com.nx.seata.multiple.service.impl;


import com.nx.seata.multiple.annotation.DataSource;
import com.nx.seata.multiple.config.DataSourceKey;
import com.nx.seata.multiple.config.DynamicDataSourceContextHolder;
import com.nx.seata.multiple.entity.Storage;
import com.nx.seata.multiple.mapper.StorageMapper;
import com.nx.seata.multiple.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.nx.seata.multiple.config.DataSourceKey.STORAGE;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    
    @Autowired
    private StorageMapper storageMapper;

    @DataSource(STORAGE)
    @Transactional//(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deduct(String commodityCode, int count){
        
        log.info("=============扣减库存=================");
        //切换数据源
     //   log.info("当前 XID: {}", RootContext.getXID());
        // 检查库存
        checkStock(commodityCode,count);
        
        log.info("开始扣减 {} 库存", commodityCode);
        Integer record = storageMapper.reduceStorage(commodityCode,count);
        log.info("扣减 {} 库存结果:{}", commodityCode, record > 0 ? "操作成功" : "扣减库存失败");
    }
    
    private void checkStock(String commodityCode, int count){
        
        log.info("检查 {} 库存", commodityCode);
        Storage storage = storageMapper.findByCommodityCode(commodityCode);
        if(storage == null){
            throw new RuntimeException("库存不存在");
        }
        if (storage.getCount() < count) {
            log.warn("{} 库存不足，当前库存:{}", commodityCode, count);
            throw new RuntimeException("库存不足");
        }
        
    }
    
}

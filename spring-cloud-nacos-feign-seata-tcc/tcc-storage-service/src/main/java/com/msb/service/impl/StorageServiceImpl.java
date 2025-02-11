package com.msb.service.impl;

import com.msb.service.StorageService;
import com.msb.service.TccStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    

    @Autowired
    TccStorageService tccStorageService;

    /**
     * 扣减库存
     * @param commodityCode
     * @param count
     */
    @Override
    public void deduct(String commodityCode, int count){
        tccStorageService.tryDeduct(commodityCode,count);
    }




    
}

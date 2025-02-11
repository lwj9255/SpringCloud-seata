package com.msb.service.impl;

import com.msb.service.AccountService;
import com.msb.service.TccAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    

    @Autowired
    private TccAccountService tccAccountService;
    
    /**
     * 扣减用户金额
     * @param userId
     * @param money
     */
    @Override
    public void debit(String userId, int money){
        tccAccountService.tryDebit(userId,money);
    }

}

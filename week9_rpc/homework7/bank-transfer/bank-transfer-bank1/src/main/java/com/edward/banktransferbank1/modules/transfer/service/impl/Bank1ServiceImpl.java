package com.edward.banktransferbank1.modules.transfer.service.impl;

import com.edward.banktransferapi.common.Bank1Service;
import com.edward.banktransferbank1.modules.transfer.service.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0")
public class Bank1ServiceImpl implements Bank1Service {
    @Autowired
    private AccountService accountService;

    @Override
    public Boolean transfer(Long uid, Long amount, Integer type) {
        accountService.subtractBalance(uid, amount, type);
        return true;
    }
}

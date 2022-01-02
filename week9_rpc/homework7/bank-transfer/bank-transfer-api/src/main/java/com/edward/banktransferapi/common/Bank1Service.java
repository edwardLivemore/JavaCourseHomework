package com.edward.banktransferapi.common;

import org.dromara.hmily.annotation.Hmily;

public interface Bank1Service {
    @Hmily
    Boolean transfer(Long uid, Long amount, Integer type);
}

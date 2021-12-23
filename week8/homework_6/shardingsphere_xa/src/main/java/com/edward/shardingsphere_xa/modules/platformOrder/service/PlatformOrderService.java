package com.edward.shardingsphere_xa.modules.platformOrder.service;

import com.edward.shardingsphere_xa.modules.platformOrder.model.PlatformOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eagle
 * @since 2021-12-23
 */
public interface PlatformOrderService extends IService<PlatformOrder> {

    void createOrder(String code, long userId, int itemNum, List<String> itemNames, List<BigDecimal> itemPrices);

    void truncate();

    void createOrderError(String code, long userId, int itemNum, List<String> itemNames, List<BigDecimal> itemPrices);
}

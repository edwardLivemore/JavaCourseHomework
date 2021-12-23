package com.edward.shardingsphere_xa.modules.platformOrderItem.service;

import com.edward.shardingsphere_xa.modules.platformOrderItem.model.PlatformOrderItem;
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
public interface PlatformOrderItemService extends IService<PlatformOrderItem> {

    void truncate();

    void createOrderItems(Long orderId, int itemNum, List<String> itemNames, List<BigDecimal> itemPrices);
}

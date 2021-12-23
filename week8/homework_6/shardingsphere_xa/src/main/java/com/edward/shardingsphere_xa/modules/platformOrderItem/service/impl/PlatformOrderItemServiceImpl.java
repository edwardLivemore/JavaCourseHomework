package com.edward.shardingsphere_xa.modules.platformOrderItem.service.impl;

import com.edward.shardingsphere_xa.modules.platformOrderItem.model.PlatformOrderItem;
import com.edward.shardingsphere_xa.modules.platformOrderItem.mapper.PlatformOrderItemMapper;
import com.edward.shardingsphere_xa.modules.platformOrderItem.service.PlatformOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eagle
 * @since 2021-12-23
 */
@Service
public class PlatformOrderItemServiceImpl extends ServiceImpl<PlatformOrderItemMapper, PlatformOrderItem> implements PlatformOrderItemService {

    @Override
    public void truncate() {
        getBaseMapper().truncate();
    }

    @Override
    public void createOrderItems(Long orderId, int itemNum, List<String> itemNames, List<BigDecimal> itemPrices) {
        List<PlatformOrderItem> orderItems = new ArrayList<>();
        for(int i = 0; i < itemNum; i++){
            PlatformOrderItem item = PlatformOrderItem.builder()
                    .orderId(orderId).name(itemNames.get(i)).price(itemPrices.get(i)).build();
            orderItems.add(item);
        }
        saveBatch(orderItems);
    }
}

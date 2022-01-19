package com.edward.orderdemo.modules.orders.service.impl;

import com.edward.orderdemo.modules.orders.model.Orders;
import com.edward.orderdemo.modules.orders.mapper.OrdersMapper;
import com.edward.orderdemo.modules.orders.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eagle
 * @since 2022-01-19
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Override
    public void addOrders(int size) {
        List<Orders> orders = new ArrayList<>();
        for(long i = 0; i < size; i++) {
            Orders order = new Orders();
            order.setUserId(i + 1);
            order.setProductId(i + 1);
            order.setStatus(0);
            orders.add(order);
        }
        saveBatch(orders);
    }

    @Override
    public void truncate() {
        baseMapper.truncate();
    }
}

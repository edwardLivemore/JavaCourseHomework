package com.edward.orderdemo.modules.orders.service;

import com.edward.orderdemo.modules.orders.model.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eagle
 * @since 2022-01-19
 */
public interface OrdersService extends IService<Orders> {

    void addOrders(int size);

    void truncate();
}

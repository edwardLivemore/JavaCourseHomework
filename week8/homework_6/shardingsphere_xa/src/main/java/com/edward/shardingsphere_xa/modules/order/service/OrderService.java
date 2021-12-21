package com.edward.shardingsphere_xa.modules.order.service;

import com.edward.shardingsphere_xa.modules.order.model.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eagle
 * @since 2021-12-21
 */
public interface OrderService extends IService<Order> {

    void truncate();

    void addOrders(List<Order> orders) throws Exception;

    void addOrdersError(List<Order> orders) throws Exception;
}

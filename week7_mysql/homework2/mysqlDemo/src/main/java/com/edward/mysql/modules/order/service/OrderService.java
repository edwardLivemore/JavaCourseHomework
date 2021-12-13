package com.edward.mysql.modules.order.service;

import com.edward.mysql.modules.order.model.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eagle
 * @since 2021-12-13
 */
public interface OrderService extends IService<Order> {

    void insertByLoop();

    void truncateTable();

    void insertByBatch();

    void insert();
}

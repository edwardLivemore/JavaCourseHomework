package com.edward.multids_v1.modules.order.service;

import com.edward.multids_v1.annotation.ReadOnly;
import com.edward.multids_v1.modules.order.model.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eagle
 * @since 2021-12-14
 */
public interface OrderService extends IService<Order> {

    void truncate();

    void insert(List<Order> orders);

    List<Order> selectList();

    void updateOrder(int id, String code);

    Order selectOrder(int id);

    void deleteOrder(int id);
}

package com.edward.multids_v1.modules.order.service.impl;

import com.edward.multids_v1.annotation.ReadOnly;
import com.edward.multids_v1.modules.order.mapper.OrderMapper;
import com.edward.multids_v1.modules.order.model.Order;
import com.edward.multids_v1.modules.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eagle
 * @since 2021-12-14
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public void truncate() {
        log.info("正在清理订单表");
        getBaseMapper().truncate();
    }

    @Override
    @ReadOnly
    public void insert(List<Order> orders) {
        log.info("正在插入订单记录");
        saveBatch(orders);
    }

    @Override
    @ReadOnly
    public List<Order> selectList() {
        log.info("正在获取所有订单信息");
        return list();
    }

    @Override
    public void updateOrder(int id, String code) {
        log.info("正在更新订单{}的编号为{}", id, code);
        lambdaUpdate()
                .set(Order::getCode, code)
                .eq(Order::getId, id)
                .update();
    }

    @Override
    @ReadOnly
    public Order selectOrder(int id) {
        log.info("正在获取订单{}的信息", id);
        return getById(id);
    }

    @Override
    public void deleteOrder(int id) {
        log.info("正在删除订单{}", id);
        getBaseMapper().deleteById(id);
    }
}

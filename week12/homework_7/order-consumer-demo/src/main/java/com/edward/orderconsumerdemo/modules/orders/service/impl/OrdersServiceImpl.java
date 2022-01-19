package com.edward.orderconsumerdemo.modules.orders.service.impl;

import com.edward.orderconsumerdemo.modules.orders.model.Orders;
import com.edward.orderconsumerdemo.modules.orders.mapper.OrdersMapper;
import com.edward.orderconsumerdemo.modules.orders.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

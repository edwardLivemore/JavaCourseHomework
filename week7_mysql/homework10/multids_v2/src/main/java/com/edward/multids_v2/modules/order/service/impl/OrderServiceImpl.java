package com.edward.multids_v2.modules.order.service.impl;

import com.edward.multids_v2.modules.order.model.Order;
import com.edward.multids_v2.modules.order.mapper.OrderMapper;
import com.edward.multids_v2.modules.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eagle
 * @since 2021-12-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}

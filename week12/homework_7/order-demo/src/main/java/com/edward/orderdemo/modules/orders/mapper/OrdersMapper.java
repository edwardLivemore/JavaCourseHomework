package com.edward.orderdemo.modules.orders.mapper;

import com.edward.orderdemo.modules.orders.model.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eagle
 * @since 2022-01-19
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    void truncate();
}

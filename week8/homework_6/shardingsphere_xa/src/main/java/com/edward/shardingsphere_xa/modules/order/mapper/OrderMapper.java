package com.edward.shardingsphere_xa.modules.order.mapper;

import com.edward.shardingsphere_xa.modules.order.model.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eagle
 * @since 2021-12-21
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    void truncate();
}

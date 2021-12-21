package com.edward.shardingsphere_jdbc.modules.order.mapper;

import com.edward.shardingsphere_jdbc.modules.order.model.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eagle
 * @since 2021-12-20
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    void truncate();
}

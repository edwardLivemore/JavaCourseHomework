package com.edward.multids_v2.modules.order.mapper;

import com.edward.multids_v2.modules.order.model.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eagle
 * @since 2021-12-17
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    void truncate();
}

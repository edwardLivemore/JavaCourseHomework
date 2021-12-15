package com.edward.multids_v1.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edward.multids_v1.modules.order.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eagle
 * @since 2021-12-14
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    void truncate();
}

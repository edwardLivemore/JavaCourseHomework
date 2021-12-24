package com.edward.shardingsphere_xa.modules.platformOrderItem.mapper;

import com.edward.shardingsphere_xa.modules.platformOrderItem.model.PlatformOrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eagle
 * @since 2021-12-23
 */
@Mapper
public interface PlatformOrderItemMapper extends BaseMapper<PlatformOrderItem> {

    void truncate();
}

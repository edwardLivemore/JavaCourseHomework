package com.edward.shardingsphere_xa.modules.platformOrder.mapper;

import com.edward.shardingsphere_xa.modules.platformOrder.model.PlatformOrder;
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
public interface PlatformOrderMapper extends BaseMapper<PlatformOrder> {

    void truncate();
}

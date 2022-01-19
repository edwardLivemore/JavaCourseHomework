package com.edward.redisdemo.modules.stock.mapper;

import com.edward.redisdemo.modules.stock.model.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eagle
 * @since 2022-01-13
 */
public interface StockMapper extends BaseMapper<Stock> {

    void truncate();
}

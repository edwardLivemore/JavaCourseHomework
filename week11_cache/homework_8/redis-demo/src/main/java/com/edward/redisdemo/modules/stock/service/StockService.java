package com.edward.redisdemo.modules.stock.service;

import com.edward.redisdemo.modules.stock.model.Stock;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eagle
 * @since 2022-01-13
 */
public interface StockService extends IService<Stock> {

    void truncate();

    void addStock(long productId, long stockNum);
}

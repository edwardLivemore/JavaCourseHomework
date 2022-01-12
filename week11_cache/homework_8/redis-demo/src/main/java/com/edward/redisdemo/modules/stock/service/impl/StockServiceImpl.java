package com.edward.redisdemo.modules.stock.service.impl;

import com.edward.redisdemo.modules.stock.model.Stock;
import com.edward.redisdemo.modules.stock.mapper.StockMapper;
import com.edward.redisdemo.modules.stock.service.StockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eagle
 * @since 2022-01-13
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Override
    public void truncate() {
        getBaseMapper().truncate();
    }

    @Override
    public void addStock(long productId, long stockNum) {
        save(Stock.builder().productId(productId).stockNum(stockNum).build());
    }
}

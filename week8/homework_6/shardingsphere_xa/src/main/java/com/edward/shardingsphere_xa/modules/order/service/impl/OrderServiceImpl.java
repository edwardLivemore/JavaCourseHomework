package com.edward.shardingsphere_xa.modules.order.service.impl;

import com.edward.shardingsphere_xa.modules.order.model.Order;
import com.edward.shardingsphere_xa.modules.order.mapper.OrderMapper;
import com.edward.shardingsphere_xa.modules.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.apache.shardingsphere.transaction.xa.XAShardingTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eagle
 * @since 2021-12-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public void truncate() {
        getBaseMapper().truncate();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public void addOrders(List<Order> orders) throws Exception {
        saveBatch(orders);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public void addOrdersError(List<Order> orders) throws Exception {
        for(int i = 0; i < orders.size(); i++){
            if(i == 1){
                throw new Exception();
            }else{
                save(orders.get(i));
            }
        }
    }
}

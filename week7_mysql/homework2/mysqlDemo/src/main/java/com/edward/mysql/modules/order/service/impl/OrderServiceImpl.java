package com.edward.mysql.modules.order.service.impl;

import com.edward.mysql.modules.order.model.Order;
import com.edward.mysql.modules.order.mapper.OrderMapper;
import com.edward.mysql.modules.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eagle
 * @since 2021-12-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<Order> orders;

    public OrderServiceImpl(){
        buildOrders();
    }

    @Override
    public void insert() {
        System.out.println("正在普通循环插入100万条数据...");
        String sql = "insert into `order` (code, user_id, sku_ids, nums, total_price, pay_method) values ('1', 1, '[1]', '[1]', 1, 1)";
        for(long i = 1; i <= 1000000; i++){
            jdbcTemplate.execute(sql);
        }
        System.out.println("普通循环插入100万条数据完成");
    }

    @Override
    public void insertByLoop() {
        System.out.println("正在循环插入100万条数据...");
        for (Order order : orders) {
            save(order);
        }
        System.out.println("循环插入100万条数据完成");
    }

    @Override
    public void truncateTable() {
        String sql = "TRUNCATE TABLE `order`";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void insertByBatch() {
        System.out.println("正在批量插入100万条数据...");
        // 分组
        List<List<Order>> groups = averageSplit(orders, 10);
        // 多线程插入数据
        groups.parallelStream().forEach(group -> saveBatch(group, 10000));
        System.out.println("批量100万条数据完成");
    }

    private <T> List<List<T>> averageSplit(List<T> source, int num) {
        List<List<T>> result = new ArrayList<>();
        int groupSize = source.size() / num;
        for(int i = 0; i < num; i++){
            List<T> tmp;
            if(i == num - 1){
                tmp = source.subList(i * groupSize, source.size());
            }else{
                tmp = source.subList(i * groupSize, (i + 1) * groupSize);
            }
            result.add(tmp);
        }
        return result;
    }

    private void buildOrders() {
        orders = new ArrayList<>();
        for(long i = 1; i <= 1000000; i++){
            Order order = Order.builder()
                    .code(String.valueOf(i))
                    .userId(i)
                    .skuIds("[" + i + "]")
                    .nums("[" + i + "]")
                    .totalPrice(new BigDecimal(1))
                    .payMethod(1)
                    .build();
            orders.add(order);
        }
    }
}

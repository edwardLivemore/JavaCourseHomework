package com.edward.multids_v2.modules.order.controller;


import com.edward.multids_v2.modules.order.model.Order;
import com.edward.multids_v2.modules.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author eagle
 * @since 2021-12-17
 */
@Component
public class OrderController implements ApplicationRunner {
    @Autowired
    private OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 准备数据
        List<Order> orders = buildOrders();

        // 1. 清空数据(主库)
        orderService.truncate();

        // 2. 插入数据(主库)
        orderService.insert(orders);

        // 3. 读取数据(从库)
        List<Order> list = orderService.selectList();
        System.out.println(list);

        // 4. 更新数据(主库)
        orderService.updateOrder(1, "new 001");
        Order order = orderService.selectOrder(1);
        System.out.println(order);

        // 5. 删除数据(主库)
        orderService.deleteOrder(2);

        // 6. 读取数据(从库)
        list = orderService.selectList();
        System.out.println(list);
    }

    private List<Order> buildOrders() {
        List<Order> orders = new ArrayList<>();
        for (long i = 1; i <= 3; i++){
            Order order = Order.builder()
                    .code(String.valueOf(i))
                    .userId(i)
                    .skuIds("[" + i + "]")
                    .nums("[" + i + "]")
                    .totalPrice(new BigDecimal(i))
                    .payMethod(1)
                    .build();
            orders.add(order);
        }
        return orders;
    }
}


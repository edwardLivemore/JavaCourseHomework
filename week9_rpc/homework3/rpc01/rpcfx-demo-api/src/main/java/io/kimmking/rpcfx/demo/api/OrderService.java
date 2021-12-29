package io.kimmking.rpcfx.demo.api;

public interface OrderService extends Service {

    Order findOrderById(int id);

}

package io.kimmking.rpcfx.demo.api;

public interface UserService extends Service {

    User findById(int id);

}

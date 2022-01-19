package com.edward.redisdemo.modules.stock.controller;


import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author eagle
 * @since 2022-01-13
 */
@RestController
@RequestMapping("/stock/stock")
@Slf4j
public class StockController implements ApplicationRunner {
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 初始化库存数量
        initRedisStock();
        getProductStock(1);
        getProductStock(2);

        // 分布式计数器
        calculator();

        // 分布式锁
        lock();

        Thread.sleep(5000);

        // 退出程序
        exit();
    }

    private void lock() {
        // 设置两个线程分别将库存清零
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 清零商品1库存
        executorService.execute(() -> {
            RLock lock = redissonClient.getLock("lock");
            try {
                lock.lock();
                String KEY = "stock:1";
                RAtomicLong stockNum = redissonClient.getAtomicLong(KEY);
                while (stockNum.get() > 0) {
                    stockNum.decrementAndGet();
                    stockNum = redissonClient.getAtomicLong(KEY);
                    getProductStock(1);
                }
            }finally {
                lock.unlock();
            }
        });

        // 清零商品2库存
        executorService.execute(() -> {
            RLock lock = redissonClient.getLock("lock");
            try {
                lock.lock();
                String KEY = "stock:2";
                RAtomicLong stockNum = redissonClient.getAtomicLong(KEY);
                while (stockNum.get() > 0) {
                    stockNum.decrementAndGet();
                    stockNum = redissonClient.getAtomicLong(KEY);
                    getProductStock(2);
                }
            }finally {
                lock.unlock();
            }
        });
    }

    private void calculator() {
        // 商品1库存-1
        decreaseProductStock(1);

        // 获取商品1的库存数量
        getProductStock(1);

        // 商品2库存-1
        decreaseProductStock(2);

        // 获取商品2的库存数量
        getProductStock(2);
    }

    private void getProductStock(int productId) {
        String KEY = "stock:" + productId;
        RAtomicLong atomicLong = redissonClient.getAtomicLong(KEY);
        log.info("商品{}的库存数量为: {}", productId, atomicLong);
    }

    private void decreaseProductStock(int productId) {
        log.info("正在删减商品{}的库存...", productId);
        String KEY = "stock:" + productId;
        redissonClient.getAtomicLong(KEY).decrementAndGet();
        log.info("删减成功");
    }

    private void exit() {
        redissonClient.shutdown();
        System.exit(0);
    }

    private void initRedisStock() {
        log.info("正在初始化redis库存...");
        redissonClient.getKeys().deleteByPattern("stock*");

        // 商品1, 库存100
        redissonClient.getAtomicLong("stock:1").set(100L);

        // 商品2, 库存200
        redissonClient.getAtomicLong("stock:2").set(200L);

        log.info("正在初始化redis库存完成");
    }
}


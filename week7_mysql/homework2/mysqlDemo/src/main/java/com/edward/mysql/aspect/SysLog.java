package com.edward.mysql.aspect;

import com.edward.mysql.modules.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SysLog {
    @Autowired
    private OrderService orderService;

    @Pointcut("execution(* com.edward.mysql.modules.*.service..*.insert*(..))")
    public void sysLog(){}

    @Around("sysLog()")
    public void logger(final ProceedingJoinPoint pjp) throws Throwable {
        // 清空表
        orderService.truncateTable();

        // 计算插入数据的用时
        long start = System.currentTimeMillis();
        pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("执行时间: " + (end - start) + "ms");
    }
}

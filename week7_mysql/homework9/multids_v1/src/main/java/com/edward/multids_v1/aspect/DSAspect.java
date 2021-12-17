package com.edward.multids_v1.aspect;

import com.edward.multids_v1.annotation.DS;
import com.edward.multids_v1.dataSource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Random;

@Aspect
@Slf4j
@Component
public class DSAspect {
    private final Random random = new Random();

    @Around("@annotation(ds)")
    public Object around(ProceedingJoinPoint pjp, DS ds) throws Throwable {

        if(ds.value().equals("master") || StringUtils.isEmpty(ds.value())){
            log.info("switch datasource : master");
            DynamicDataSource.setDataSource("master");
        }else{
            // 负载均衡(随机策略)
            if(random.nextInt(2) == 0){
                log.info("switch datasource : slave1");
                DynamicDataSource.setDataSource("slave1");
            }else {
                log.info("switch datasource : slave2");
                DynamicDataSource.setDataSource("slave2");
            }
        }
        return pjp.proceed();
    }
}

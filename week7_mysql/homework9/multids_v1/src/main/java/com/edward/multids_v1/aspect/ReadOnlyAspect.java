package com.edward.multids_v1.aspect;

import com.edward.multids_v1.annotation.ReadOnly;
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
public class ReadOnlyAspect {
    private final Random random = new Random();

    @Around("@annotation(readOnly)")
    public Object around(ProceedingJoinPoint pjp, ReadOnly readOnly) throws Throwable {
        if(StringUtils.isNotEmpty(readOnly.value())){
            // 负载均衡(随机策略)
            int num = random.nextInt(2);
            DynamicDataSource.setDataSource("slave" + (num + 1));
            log.info("switch datasource : {}", DynamicDataSource.getDataSource());
        }
        Object proceed = pjp.proceed();
        // 清理掉当前设置的数据源，让默认的数据源不受影响
        DynamicDataSource.clearDataSource();
        return proceed;
    }
}

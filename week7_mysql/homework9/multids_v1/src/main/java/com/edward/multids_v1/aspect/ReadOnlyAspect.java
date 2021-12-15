package com.edward.multids_v1.aspect;

import com.edward.multids_v1.annotation.ReadOnly;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Slf4j
@Configuration
public class ReadOnlyAspect {
    @Around("@annotation(readOnly)")
    public Object around(ProceedingJoinPoint pjp, ReadOnly readOnly) throws Throwable {
        System.out.println("readOnly method start...");
        Object result = pjp.proceed();
        System.out.println("readOnly method end...");
        return result;
    }
}

package com.edward.multids_v1.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解ReadOnly, slave库只读用
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReadOnly {
    String value() default "";
}

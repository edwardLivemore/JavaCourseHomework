package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Printer implements Serializable, BeanNameAware, ApplicationContextAware,
        InitializingBean, DisposableBean {
    private Integer id;
    private String name;
    private String beanName;
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        System.out.println("call InitializingBean afterPropertiesSet, bean : " + beanName);
    }

    @Override
    public void destroy() {
        System.out.println("call DisposableBean destroy, bean : " + beanName);
    }

    public void print() {
        System.out.println("call print method, bean : " + this.name + "   context.getBeanDefinitionNames() ===>> "
                + String.join(",", applicationContext.getBeanDefinitionNames()));

    }

    public void initMethod(){
        System.out.println("call initMethod, bean : " + this.beanName);
    }

    public void destroyMethod(){
        System.out.println("call destroyMethod, bean : " + this.beanName);
    }
}

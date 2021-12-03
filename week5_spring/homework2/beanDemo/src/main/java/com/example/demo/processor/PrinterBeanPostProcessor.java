package com.example.demo.processor;

import com.example.demo.pojo.Printer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PrinterBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Printer){
            System.out.println("call BeanPostProcessor before initialization, bean : " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Printer){
            System.out.println("call BeanPostProcessor after initialization, bean : " + beanName);
        }
        return bean;
    }
}

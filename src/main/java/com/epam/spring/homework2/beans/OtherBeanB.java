package com.epam.spring.homework2.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class OtherBeanB implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanParent) {
            if (((BeanParent) bean).getName() != null && ((BeanParent) bean).getValue() >= 0) {
                System.out.println(((BeanParent) bean).getName() + " is valid");
            } else {
                System.out.println(((BeanParent) bean).getName() + " is invalid");
            }
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}

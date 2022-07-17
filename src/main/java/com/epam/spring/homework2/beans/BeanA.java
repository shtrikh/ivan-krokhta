package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanA extends BeanParent implements InitializingBean, DisposableBean {
    private String name;
    private double value;

    public BeanA(String name, double value) {
        super(name, value);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanA destroy method");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanA afterPropertiesSet method");
    }
}

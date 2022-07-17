package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanB extends BeanParent {
    public BeanB(String name, double value) {
        super(name, value);
    }

    public void customInitMethod() {
        System.out.println("BeanB custom init method");
    }

    public void customDestroyMethod() {
        System.out.println("BeanB custom destroy method");
    }
}

package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanD extends BeanParent {
    public BeanD(String name, double value) {
        super(name, value);
    }


    public void customInitMethod() {
        System.out.println("BeanD custom init method");
    }

    public void customDestroyMethod() {
        System.out.println("BeanD custom destroy method");
    }
}

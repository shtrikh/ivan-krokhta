package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanC extends BeanParent {
    public BeanC(String name, double value) {
        super(name, value);
    }

    public void customInitMethod() {
        System.out.println("BeanC custom init method");
    }

    public void customDestroyMethod() {
        System.out.println("BeanC custom destroy method");
    }
}

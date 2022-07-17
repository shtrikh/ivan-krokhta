package com.epam.spring.homework2.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class BeanF extends BeanParent {
    private String name;
    private double value;

    public BeanF(String name, double value) {
        super(name, value);
    }
}

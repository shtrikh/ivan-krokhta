package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanE extends BeanParent{
    private String name;
    private double value;

    public BeanE(String name, double value) {
        super(name, value);
    }

    @Override
    public String toString() {
        return "BeanE{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @PostConstruct
    public void postConstructMethod(){
        System.out.println("BeanE postConstructMethod");
    }

    @PreDestroy
    public void preDestroyMethod(){
        System.out.println("BeanE preDestroyMethod");
    }
}

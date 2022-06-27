package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanC extends BeanParent{
    private String name;
    private double value;

    public BeanC(String name, double value) {
        super(name, value);
    }

    @Override
    public String toString() {
        return "BeanC{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public void customInitMethod(){
        System.out.println("BeanC custom init method");
    }

    public void customDestroyMethod(){
        System.out.println("BeanC custom destroy method");
    }
}

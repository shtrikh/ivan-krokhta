package com.epam.spring.homework2.beans;

public class BeanParent {
    private String name;
    private double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public BeanParent(String name, double value) {
        this.name = name;
        this.value = value;
    }


    @Override
    public String toString() {
        return "BEAN{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
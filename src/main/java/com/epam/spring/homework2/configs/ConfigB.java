package com.epam.spring.homework2.configs;

import com.epam.spring.homework2.beans.BeanA;
import com.epam.spring.homework2.beans.BeanE;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ConfigB {
    @Bean
    public BeanA beanA() {
        return new BeanA("BeanA", 111);
    }

    @Bean
    public BeanE beanE() {
        return new BeanE("BeanE", 999);
    }

    @Bean
    @Lazy
    public BeanF beanF() {
        return new BeanF("BeanF", 444);
    }
}

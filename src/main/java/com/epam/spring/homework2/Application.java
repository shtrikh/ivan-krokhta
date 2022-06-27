package com.epam.spring.homework2;

import com.epam.spring.homework2.configs.ConfigA;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ConfigA.class);
        Arrays.asList(context.getBeanDefinitionNames()).forEach(System.out::println);
        context.close();
    }
}

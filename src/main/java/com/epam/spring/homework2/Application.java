package com.epam.spring.homework2;

import com.epam.spring.homework2.configs.ConfigA;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigA.class);

        Arrays.stream(context.getBeanDefinitionNames())
                .map(context::getBeanDefinition)
                .forEach(System.out::println);

        ((ConfigurableApplicationContext) context).close();
    }
}

package com.epam.spring.homework2.configs;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@Import(ConfigB.class)
@PropertySource("beans.properties")
@ComponentScan("com.epam.spring.homework2.beans")
public class ConfigA {

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanD")
    public BeanB beanB(@Value("${bean.b.name}") String name, @Value("${bean.b.value}") double value) {
        return new BeanB(name, value);
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanB")
    public BeanC beanC(@Value("${bean.c.name}") String name, @Value("${bean.c.value}") double value) {
        return new BeanC(name, value);
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanD beanD(@Value("${bean.d.name}") String name, @Value("${bean.d.value}") double value) {
        return new BeanD(name, value);
    }
}

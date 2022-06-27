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

    @Value("${bean.b.name}")
    private String beanBName;
    @Value("${bean.b.value}")
    private double beanBValue;
    @Value("${bean.c.name}")
    private String beanCName;
    @Value("${bean.c.value}")
    private double beanCValue;
    @Value("${bean.d.name}")
    private String beanDName;
    @Value("${bean.d.value}")
    private double beanDValue;

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanD")
    public BeanB beanB(){
        return new BeanB(beanBName, beanBValue);
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanB")
    public BeanC beanC(){
        return new BeanC(beanCName, beanCValue);
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanD beanD(){
        return new BeanD(beanDName, beanDValue);
    }

}

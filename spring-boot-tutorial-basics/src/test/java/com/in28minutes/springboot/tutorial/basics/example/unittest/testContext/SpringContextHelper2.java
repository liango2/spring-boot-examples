package com.in28minutes.springboot.tutorial.basics.example.unittest.testContext;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

@Service
public class SpringContextHelper2 extends ApplicationObjectSupport {


    //提供一个接口，获取容器中的Bean实例，根据名称获取
    public Object getBean(String beanName)
    {
        return getApplicationContext().getBean(beanName);
    }

}

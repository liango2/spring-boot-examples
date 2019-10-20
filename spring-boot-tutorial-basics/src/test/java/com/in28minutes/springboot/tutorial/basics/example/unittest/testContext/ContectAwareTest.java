package com.in28minutes.springboot.tutorial.basics.example.unittest.testContext;

import com.in28minutes.springboot.tutorial.basics.example.unittesting.BusinessService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HanLiangYuan
 * @version 1.0
 * @since 2019/10/19 21:06
 */
public class ContectAwareTest {

    private SpringContextHelper helper;
    private SpringContextHelper2 helper2;
    ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("application-test.xml");
        //context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // context= new ClassPathXmlApplicationContext(new String[]{"spring-config.xml","spring-config-struts.xml"});
        helper = (SpringContextHelper) context.getBean("springContextHelper");
        helper2 = (SpringContextHelper2) context.getBean("springContextHelper2");

        List<String> beans = Arrays.asList(context.getBeanDefinitionNames()).stream().sorted().collect(Collectors.toList());
        System.out.println("打印所有beans=" + beans);
    }

    @Test
    public void test() {

    }

    @Test
    public void testGetBean() {
        // 能过 SpringContextHelper 获取容器中的Bean
        //String bean = (String) helper.getBean("xmlStringBean1");
        //System.out.println("bean = " + bean);
        //
        //BusinessService businessService = (BusinessService) helper.getBean("businessService");
        //businessService.foo(123);
        //String bean = (String)context.getBean("xmlStringBean1");
        // System.out.println("bean = " + bean);
    }

    @Test
    public void testGetBean2() {
        // 能过 SpringContextHelper2 获取容器中的Bean
        //BusinessService bean = (BusinessService) helper2.getBean("businessService");
        //bean.foo(456);
        BusinessService bean = (BusinessService) context.getBean("businessService");
        bean.foo(456);
    }

}

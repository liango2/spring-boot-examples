package com.in28minutes.springboot.tutorial.basics.example.application.context.java;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 以静态变量保存 Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext.
 *
 * 该工具类主要用于：那些没有归入spring框架管理的类却要调用spring容器中的bean提供的工具类。
 * <p>
 * 在spring中要通过IOC依赖注入来取得对应的对象，但是该类通过实现ApplicationContextAware接口，以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext.
 * <p>
 * 如此就不能说说org.springframework.context.ApplicationContextAware这个接口了：
 * <p>
 * 当一个类实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中的所有bean。换句话说，就是这个类可以直接获取spring配置文件中，所有有引用到的bean对象。
 * 链接: SpringContextHolder 静态持有SpringContext的引用 - 吴小雨 - 博客园
 *       https://www.cnblogs.com/wcyBlog/p/4657885.html
 *
 *  <bean class=" com.in28minutes.springboot.tutorial.basics.example.application.context.java.MySpringContextHolder"  />
 */
public class MySpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    //实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
    public void setApplicationContext(ApplicationContext applicationContext) {
        MySpringContextHolder.applicationContext = applicationContext;
    }


    //取得存储在静态变量中的ApplicationContext.
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    //从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }


    //从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
    //如果有多个Bean符合Class, 取出第一个.
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        @SuppressWarnings("rawtypes")
        Map beanMaps = applicationContext.getBeansOfType(clazz);
        if (beanMaps != null && !beanMaps.isEmpty()) {
            return (T) beanMaps.values().iterator().next();
        } else {
            return null;
        }
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }

}

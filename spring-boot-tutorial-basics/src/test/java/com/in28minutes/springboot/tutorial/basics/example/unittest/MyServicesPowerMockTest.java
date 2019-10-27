package com.in28minutes.springboot.tutorial.basics.example.unittest;

import com.alibaba.fastjson.JSON;
import com.in28minutes.springboot.tutorial.basics.example.MyUtils;
import com.in28minutes.springboot.tutorial.basics.example.student.Student;
import com.in28minutes.springboot.tutorial.basics.example.unittesting.BusinessService;
import com.in28minutes.springboot.tutorial.basics.example.unittesting.DataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class)
//重要的事说三遍： 如果要mock静态方法，必须要在PrepareForTest后面加上该方法所在的类。
@PrepareForTest(MyUtils.class)
//@PowerMockIgnore("javax.management.*")
public class MyServicesPowerMockTest {
    @Mock
    DataService dataServiceMock;

    @InjectMocks
    BusinessService businessImpl;

    @Before
    public void before() throws Exception {
        //MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(MyUtils.class);
    }

    @Test
    public void testJsonToString() {
        String han = JSON.toJSONString(new Student(1L, "han", "123"));
        System.out.println("han = " + han);
        //
        //System.out.println("PowerMockito.class.getClassLoader() = " + PowerMockito.class.getClassLoader());
        //System.out.println("JSON.class.getClassLoader() = " + JSON.class.getClassLoader());
        //System.out.println("Student.class.getClassLoader() = " + Student.class.getClassLoader());
        //System.out.println("businessImpl.getClass().getClassLoader() = " + businessImpl.getClass().getClassLoader());
        //
        //System.out.println("MyUtils.class.getClassLoader() = " + MyUtils.class.getClassLoader());
        //System.out.println("dataServiceMock.getClass().getDeclaringClass() = " + dataServiceMock.getClass().getDeclaringClass());


        //when(dataServiceMock.bar2(anyInt())).thenReturn(new Student(3L, "han3", "123"));
        //System.out.println("businessImpl.foo2(1).getClass().getClassLoader() = " + businessImpl.foo2(1).getClass().getClassLoader());


    }

    @Test
    public void testMethod() {
        //TODO PrivateStaticFinal.variable = 100
        PowerMockito.when(MyUtils.method()).thenReturn(100);
        Integer method = MyUtils.method();
        System.out.println("method = " + method);
        method = MyUtils.method();
        System.out.println("method = " + method);
        //  assertEquals(MyUtils.method(), 101);
    }

    @Test
    public void testFoo() {
        when(dataServiceMock.bar(any())).thenReturn(2222);
        System.out.println("businessImpl.foo(1) = " + businessImpl.foo(1));
        System.out.println("businessImpl.foo(1) = " + businessImpl.foo(1));
    }


}

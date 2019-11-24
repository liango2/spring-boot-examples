package com.in28minutes.springboot.tutorial.basics.example.unittest;

import com.in28minutes.springboot.tutorial.basics.example.unittesting.BusinessService;
import com.in28minutes.springboot.tutorial.basics.example.unittesting.DataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyMockWithSprinBootTestV3 {

	@Autowired
	private ApplicationContext context;

	@MockBean
	DataService dataServiceMock;

	@Autowired
	BusinessService businessImpl;




	@Test
	public void testFindTheGreatestFromAllData() {
		System.out.println("context = " + context);

		// 打印bean,方法1(不全)
		Arrays.asList(context.getBeanDefinitionNames()).stream().sorted().peek(System.out::println).count();
		//List<Object> singletons = new ArrayList<Object>();
		//String[] all = ctx.getBeanDefinitionNames();
		//ConfigurableListableBeanFactory clbf = ((AbstractApplicationContext) ctx).getBeanFactory();
		//for (String name : all) {
		//	Object s = clbf.getSingleton(name);
		//	if (s != null)
		//		singletons.add(s);
		//}

		// 打印bean,方法2
		AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
		if (autowireCapableBeanFactory instanceof SingletonBeanRegistry) {
			Arrays.asList( ((SingletonBeanRegistry) autowireCapableBeanFactory).getSingletonNames()).stream().sorted().peek(System.out::println).count();
		}


		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
		assertEquals(24, businessImpl.findTheGreatestFromAllData());
	}

	@Test
	public void testFindTheGreatestFromAllData_ForOneValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 15 });
		assertEquals(15, businessImpl.findTheGreatestFromAllData());
	}

	@Test
	public void testFindTheGreatestFromAllData_NoValues() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(Integer.MIN_VALUE, businessImpl.findTheGreatestFromAllData());
	}
}

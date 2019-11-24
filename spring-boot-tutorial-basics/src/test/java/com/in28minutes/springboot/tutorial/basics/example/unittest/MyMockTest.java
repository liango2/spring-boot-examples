package com.in28minutes.springboot.tutorial.basics.example.unittest;

import com.in28minutes.springboot.tutorial.basics.example.unittesting.BusinessService;
import com.in28minutes.springboot.tutorial.basics.example.unittesting.DataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyMockTest {

    @Mock
    DataService dataServiceMock;

    @InjectMocks
    BusinessService businessImpl;

    @Test
    public void testFoo() {
        when(dataServiceMock.bar(any())).thenThrow(RuntimeException.class);//.thenReturn(1111).thenReturn(2222);
        try {
            System.out.println("businessImpl.foo(1) = " + businessImpl.foo(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        reset(dataServiceMock);
        when(dataServiceMock.bar(any())).thenReturn(1111);
        when(dataServiceMock.bar(any())).thenReturn(2222);
        System.out.println("businessImpl.foo(1) = " + businessImpl.foo(1));
        System.out.println("businessImpl.foo(1) = " + businessImpl.foo(1));
        System.out.println("businessImpl.foo(1) = " + businessImpl.foo(1));
        //when(dataServiceMock.bar(any())).thenReturn(123);//.thenReturn(1111);
        //when(dataServiceMock.bar(any())).thenReturn(222).thenReturn(333);
        //when(dataServiceMock.bar(any())).thenReturn(444).thenReturn(555);
        //when(dataServiceMock.bar(3)).thenReturn(777);
        //System.out.println("businessImpl.foo(1) = " + businessImpl.foo(1));
        //System.out.println("businessImpl.foo(1) = " + businessImpl.foo(1));
        //System.out.println("businessImpl.foo(1) = " + businessImpl.foo(1));
        //System.out.println("businessImpl.foo(1) = " + businessImpl.foo(1));
        //assertEquals(888, businessImpl.foo(1));
        //assertEquals(999, businessImpl.foo(2));
        //assertEquals(777, businessImpl.foo(3));
    }

    @Test
    public void testFindTheGreatestFromAllData() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{24, 15, 3});
        assertEquals(24, businessImpl.findTheGreatestFromAllData());
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{24, 15, 3, 26});
        assertEquals(26, businessImpl.findTheGreatestFromAllData());
    }

    @Test
    public void testFindTheGreatestFromAllData_ForOneValue() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{15});
        assertEquals(15, businessImpl.findTheGreatestFromAllData());
    }

    @Test
    public void testFindTheGreatestFromAllData_NoValues() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(Integer.MIN_VALUE, businessImpl.findTheGreatestFromAllData());
    }
}

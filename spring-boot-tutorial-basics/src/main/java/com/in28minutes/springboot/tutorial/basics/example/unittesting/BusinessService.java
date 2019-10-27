package com.in28minutes.springboot.tutorial.basics.example.unittesting;

import com.alibaba.fastjson.JSON;
import com.in28minutes.springboot.tutorial.basics.example.student.Student;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
    private DataService dataService;

    public BusinessService(DataService dataService) {
        super();
        this.dataService = dataService;
    }

    public int findTheGreatestFromAllData() {
        int[] data = dataService.retrieveAllData();
        int greatest = Integer.MIN_VALUE;

        for (int value : data) {
            if (value > greatest) {
                greatest = value;
            }
        }
        return greatest;
    }


    public int foo(Integer i) {
        System.out.println("foo i = " + i);
        		Student han1 = new Student(1L, "han", "123");
        System.out.println("han = " + JSON.toJSONString(han1));
        return dataService.bar(i);

    }

    public Student foo2(int i) {
        Student student = dataService.bar2(i);
        System.out.println("JSON.toJSONString(student) = " + JSON.toJSONString(student));
        return student;
    }
}

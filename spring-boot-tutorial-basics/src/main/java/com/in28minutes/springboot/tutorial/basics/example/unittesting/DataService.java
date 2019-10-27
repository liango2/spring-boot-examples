package com.in28minutes.springboot.tutorial.basics.example.unittesting;

import com.alibaba.fastjson.JSON;
import com.in28minutes.springboot.tutorial.basics.example.student.Student;
import org.springframework.stereotype.Repository;

@Repository
public class DataService {
	public int[] retrieveAllData() {
		// Some dummy data
		// Actually this should talk to some database to get all the data
		return new int[] { 1, 2, 3, 4, 5 };
	}

	public Integer bar(Integer i) {
		System.out.println("bar i = " + i);

		return i;
	}

	public Student bar2(int i) {
		return new Student(2L, "bar2", "12345");
	}
}

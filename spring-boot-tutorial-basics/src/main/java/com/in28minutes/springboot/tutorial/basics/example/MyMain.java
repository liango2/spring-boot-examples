package com.in28minutes.springboot.tutorial.basics.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(MyMain.class, args);

		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}
}

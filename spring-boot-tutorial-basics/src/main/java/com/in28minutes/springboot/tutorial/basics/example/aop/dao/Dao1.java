package com.in28minutes.springboot.tutorial.basics.example.aop.dao;
import org.springframework.stereotype.Repository;

@Repository
public class Dao1 {

	public String retrieveSomething(){
		return "Dao1";
	}

}

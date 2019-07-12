package com.mastek.training.hrapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.entities.Employee;

// Initialize the JUnit TEst with Spring Boot Application Environment
// Spring Boot Test: used to test Spring APplicationContext 
// with all the test cases idenitfied

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrAppApplicationTests {
	
	// scan in memory all the components and provide the 
	// best match object in the component
	
	@Autowired
	EmployeeService empService;

	@Autowired
	Employee emp;
	
	@Test
	public void addEmployeeUsingService() {
		empService.registerEmployee(emp);
	}

	
	@Test
	public void simpleTest() {
		System.out.println("System Test Executed");
	}
	
}





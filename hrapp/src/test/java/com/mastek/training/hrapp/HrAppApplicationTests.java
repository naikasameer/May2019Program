package com.mastek.training.hrapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

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
		emp.setEmpno(5); 
		emp.setName("New Emp 5");
		emp.setSalary(92);
		emp = empService.registerOrUpdateEmployee(emp);
		assertNotNull(emp);
	}

	@Test
	public void findByEmpnoUsingService() {
		int empno = 1;
		assertNotNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void deleteByEmpnoUsingService() {
		int empno=2;
		empService.deleteByEmpno(empno);
		assertNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void checkFetchBySalary() {
		List<Employee> emps = empService
				.fetchEmployeesBySalaryRange(0, 1000);
		for (Employee employee : emps) {
			System.out.println(employee);
		}
		
		assertEquals(emps.size(),2);
	}
	
	@Test
	public void simpleTest() {
		System.out.println("System Test Executed");
	}
	
}





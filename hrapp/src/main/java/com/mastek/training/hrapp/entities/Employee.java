package com.mastek.training.hrapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component 
@Scope("prototype") // one copy for each test case
@Entity  // declares the class as an Entity
@Table(name="JPA_EMPLOYEE") // declaring the table name for the class
@EntityListeners({EmployeeLifecycleListener.class})
@NamedQueries({
  @NamedQuery(name="Employee.findBySalary",
   query="select e from Employee e where e.salary between :min and :max")	
})
public class Employee 
	implements Serializable { // manage serialization of Objects
	
	@Value("-1")
	private int empno;
	
	@Value("Default Employee")
	private String name;
	
	@Value("100.0")
	private double salary;

	public Employee() {
		System.out.println("Emplyee Created");
	}
	
	@Id	// declare the property as Primary Key
	@Column(name="employee_number") // declare the column name 
	@GeneratedValue(strategy=GenerationType.AUTO) // Auto-numbering
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	
	@Column(name="employee_name",nullable=false,length=45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() { // JPA will default configurations 
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}


	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", name=" + name + ", salary=" + salary + "]";
	}


}

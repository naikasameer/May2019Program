package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="JPA_Department")
public class Department implements Serializable{

	private int deptno;
	private String name;
	private String location;
	// OneTOMany: One Department has many Employees
	private Set<Employee> members = new HashSet<>();
	
	//@OneToMany: used on the get method of set to configure association
	// fetch=LAZY: delay the initialization 
	//			until method getMembers() is called using 
	//			additional select query [default value]
	//		 EAGER: Initialize the collection on entity find 
	//				Post load event
	// cascade=All the Entity operation done on Department
	//		 would be performed on each associated employee object
	//		ALL: [Persist,Merge,Delete,Refresh]
	//  mappedBy: identifies the propertyname in Child class
	//			where the JoinColumn configuration is present
	//			JoinColumn::ForeignKey
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,
			mappedBy="currentDepartment")
	@XmlTransient
	public Set<Employee> getMembers() {
		return members;
	}

	public void setMembers(Set<Employee> members) {
		this.members = members;
	}

	public Department() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getDeptno() {
		return deptno;
	}


	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public String toString() {
		return "Department [deptno=" + deptno + ", name=" + name + ", location=" + location + "]";
	}
	
	
}

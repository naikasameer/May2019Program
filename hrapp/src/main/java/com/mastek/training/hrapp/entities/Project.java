package com.mastek.training.hrapp.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="JPA_Project")
public class Project {

	private int projectId;
	private String name;
	private String customerName;
	
	private Set<Employee> team=new HashSet<>();
	
	//mappedBy: check the configuration for Many to Many association
	// In Employee class getAssignments() method
	@ManyToMany(mappedBy="assignments")
	public Set<Employee> getTeam() {
		return team;
	}
	public void setTeam(Set<Employee> team) {
		this.team = team;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", name=" + name + ", customerName=" + customerName + "]";
	}
	
	public Project() {
		// TODO Auto-generated constructor stub
	}
	
}

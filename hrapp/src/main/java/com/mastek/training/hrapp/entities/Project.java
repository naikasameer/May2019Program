package com.mastek.training.hrapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JPA_Project")
public class Project {

	private int projectId;
	private String name;
	private String customerName;
	
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

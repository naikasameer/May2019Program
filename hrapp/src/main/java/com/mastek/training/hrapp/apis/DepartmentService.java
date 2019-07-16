package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.repositories.DepartmentRepository;

//@COmponent: indicate to Spring to create an object of this class as component
//@Scope: singleton: one object for application [default]
//	      prototype: one object copy for each usage

@Component
@Scope("singleton")
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public DepartmentService() {
		System.out.println("Department Service Created");
	}
	
	public Department registerOrUpdateDepartment(Department dept) {
		dept = departmentRepository.save(dept);
		System.out.println("Department Registered "+dept);
		return dept;
	}

	public Department findByDeptno(int deptno) {
		// fetches the Department details from DB by empno
		try {
			return departmentRepository.findById(deptno).get();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteByEmpno(int deptno) {
		departmentRepository.deleteById(deptno);
	}
}



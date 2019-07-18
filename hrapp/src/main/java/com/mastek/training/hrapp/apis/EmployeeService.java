package com.mastek.training.hrapp.apis;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.entities.Employee;
import com.mastek.training.hrapp.entities.Project;
import com.mastek.training.hrapp.repositories.DepartmentRepository;
import com.mastek.training.hrapp.repositories.EmployeeRepository;
import com.mastek.training.hrapp.repositories.ProjectRepository;

//@COmponent: indicate to Spring to create an object of this class as component
//@Scope: singleton: one object for application [default]
//	      prototype: one object copy for each usage

@Component
@Scope("singleton")
@Path("/employees/") // map the URL pattern with the class as service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	public EmployeeService() {
		System.out.println("Employee Service Created");
	}
	
	@POST // HTTP Method to send the form Data
	@Path("/register") // URL Pattern
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // Form Data
	@Produces(MediaType.APPLICATION_JSON) // JSON Data
	public Employee registerOrUpdateEmployee(
				 @BeanParam Employee emp) { // Input Bean using form
		emp = employeeRepository.save(emp);
		System.out.println("Employee Registered "+emp);
		return emp;
	}

	// Use find method using PathParam
	// /find/1122 : 1122->empno to pass as param to this method
	@Path("/find/{empno}")
	@GET // HTTP Method used to call the api 
	@Produces({ // declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in JSON
		MediaType.APPLICATION_XML  // Object to be given in XML
	})
	public Employee findByEmpno(
			// Use the Path Parameter as the argument for the method
			@PathParam("empno") int empno) {
		// fetches the Employee details from DB by empno
		try {
			return employeeRepository.findById(empno).get();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET	// http method
	@Path("/fetchBySalary") // url pattern
	@Produces(MediaType.APPLICATION_JSON) // response content type
	public List<Employee> fetchEmployeesBySalaryRange(
				@QueryParam("min") double min, // param min
				@QueryParam("max")double max){ // param min
		return employeeRepository.findBySalary(min, max);
	}
	
	@DELETE // delete HTTP Method
	@Path("/delete/{empno}")
	public void deleteByEmpno(@PathParam("empno") int empno) {
		employeeRepository.deleteById(empno);
	}
	
	@GET
	@Path("/assign/department")
	@Transactional
	public Employee assignDepartment(
			@QueryParam("empno")int empno,
			@QueryParam("deptno")int deptno) {
		Employee emp = employeeRepository.findById(empno).get();
		Department dept = departmentRepository.findById(deptno).get();
		dept.getMembers().add(emp);
		emp.setCurrentDepartment(dept);
		departmentRepository.save(dept);
		employeeRepository.save(emp);
		return emp;
	}
	
	@GET
	@Path("/assign/project")
	@Transactional
	public Employee assignProject(
			@QueryParam("empno")int empno,
			@QueryParam("projectId")int projectId) {
		
		Employee emp = employeeRepository.findById(empno).get();
		Project project= projectRepository.findById(projectId).get();
		emp.getAssignments().add(project);		
		
		employeeRepository.save(emp);
		return emp;
	}
	
	
}




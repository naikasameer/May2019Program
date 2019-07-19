package com.mastek.training.hrapp.apis;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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
	private DepartmentService departmentService;
	
	@Autowired
	private ProjectService projectService;
	
	
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
	
	// SPring ensures that database session is open until
	// all the operations in this method across repositories 
	// are completed
	// It is used to fetch all collections which are
	// initialized using Lazy Initialization
	@Transactional
	@POST
	@Path("/assign/department")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Employee assignDepartment(
				@FormParam("empno") int empno, 
				@FormParam("deptno") int deptno) {
		try {
			// fetch the entities to be associated
			Employee emp = findByEmpno(empno);
			Department dept = departmentService
								.findByDeptno(deptno);
			//manage the association
			
			dept.getMembers().add(emp);// One assigned with many
			emp.setCurrentDepartment(dept);// many assigned with one

			// update the entity to save the association
			emp = registerOrUpdateEmployee(emp);
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@POST
	@Path("/assign/project")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Project> assignProject(
				@FormParam("empno") int empno, 
				@FormParam("projectId") int projectId) {
		try {
			Employee emp = findByEmpno(empno);
			Project prj = projectService.findByProjectId(projectId);
			// associate Employee with Project  
			emp.getAssignments().add(prj);
			// update the association in db, in join table 
			emp = registerOrUpdateEmployee(emp);
			
			return emp.getAssignments();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}




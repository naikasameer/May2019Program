package com.mastek.training.hrapp.apis;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

// create the Jersey Server configuration class
@Component
public class APIConfig extends ResourceConfig{

	public APIConfig() {
		//register each Service class in ResourceConfig
		register(EmployeeService.class);
		register(DepartmentService.class);
		register(ProjectService.class);
	}
}

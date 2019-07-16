package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Project;
import com.mastek.training.hrapp.repositories.ProjectRepository;

//@COmponent: indicate to Spring to create an object of this class as component
//@Scope: singleton: one object for application [default]
//	      prototype: one object copy for each usage

@Component
@Scope("singleton")
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public ProjectService() {
		System.out.println("Project Service Created");
	}
	
	public Project registerOrUpdateProject(Project prj) {
		prj = projectRepository.save(prj);
		System.out.println("Project Registered "+prj);
		return prj;
	}

	public Project findByProjectId(int projectId) {
		try {
			return projectRepository.findById(projectId).get();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void deleteByProjectId(int projectId) {
		projectRepository.deleteById(projectId);
	}
}



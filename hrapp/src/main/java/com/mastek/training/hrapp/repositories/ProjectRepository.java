package com.mastek.training.hrapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Project;

@Component
public interface ProjectRepository extends CrudRepository<Project, Integer>{

}

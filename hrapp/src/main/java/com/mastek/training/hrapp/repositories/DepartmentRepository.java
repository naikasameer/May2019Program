package com.mastek.training.hrapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Department;

@Component
public interface DepartmentRepository extends CrudRepository<Department, Integer>{

}

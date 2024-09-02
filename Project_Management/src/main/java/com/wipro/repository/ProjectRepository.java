package com.wipro.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.Project;
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	public Project findByProjectName(String projectName);

	

	

	

	

}

package com.wipro.service;

import java.util.List;

import com.wipro.dto.APIResponseDto;
import com.wipro.dto.ProjectDto;
import com.wipro.exception.ProjectIdNotFoundException;

import jakarta.validation.Valid;




public interface ProjectService {

	public ProjectDto createUser(ProjectDto projectdto);

	public List<ProjectDto> getAllProjects();

	

	public ProjectDto updateProjectById(Long x, ProjectDto projectdto) throws ProjectIdNotFoundException;

	public void deleteProjectById(Long x);

	public List<APIResponseDto> getProjectById(Long pid) throws ProjectIdNotFoundException;

	

	public ProjectDto findByProjectName(String projectName);

	

	

	

	

	

	

		
	
	
	
	

	

	

	



}

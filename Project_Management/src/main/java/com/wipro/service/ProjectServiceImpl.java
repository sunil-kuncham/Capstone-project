package com.wipro.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.dto.APIResponseDto;
import com.wipro.dto.ProjectDto;
import com.wipro.dto.TaskDto;
import com.wipro.entity.Project;
import com.wipro.exception.ProjectIdNotFoundException;
import com.wipro.repository.ProjectRepository;

import jakarta.validation.Valid;


@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProjectRepository repository;
	@Autowired
	private APIClient apiClient;
	@Override
	public ProjectDto createUser(ProjectDto projectdto) {
		Project project= modelMapper.map(projectdto,Project.class);
		Project savedProject= repository.save(project);
		

		ProjectDto dto = modelMapper.map(savedProject,ProjectDto.class);
		return dto;
	}
	@Override
	public List<ProjectDto> getAllProjects() {
		List<Project> projects=(List<Project>) repository.findAll();
		 List<ProjectDto> listdtos= new ArrayList<ProjectDto>();
		 for(Project s:projects) {
			 
			 
			 listdtos.add(modelMapper.map(s, ProjectDto.class));
		 }
		return listdtos;
	}
	
	@Override
	public ProjectDto updateProjectById(Long x, ProjectDto projectdto) throws ProjectIdNotFoundException{
		Project retrievedUser = repository.findById(x).orElseThrow(() -> new ProjectIdNotFoundException("Project not found with id: " + x));
		retrievedUser.setProjectName(projectdto.getProjectName());
		retrievedUser.setProjectDescription(projectdto.getProjectDescription());
		retrievedUser.setStartTime(projectdto.getStartTime());
		retrievedUser.setEndTime(projectdto.getEndTime());
		
		Project savedObject= repository.save(retrievedUser);
		
		ProjectDto dto = modelMapper.map(savedObject,ProjectDto.class);
		return dto;
	}
	@Override
	public void deleteProjectById(Long x) {
		repository.deleteById(x);
		
	}
	@Override
	public List<APIResponseDto> getProjectById(Long pid) throws ProjectIdNotFoundException {
		Project savedProject = repository.findById(pid).orElseThrow(() -> new ProjectIdNotFoundException("Project not found with id: " + pid));
	    System.out.println(savedProject);
	    List<TaskDto> taskDtoList = apiClient.getAllTasksByPid(savedProject.getPid());
	    System.out.println(taskDtoList);
	    ProjectDto projectDto = modelMapper.map(savedProject, ProjectDto.class);
	    
	    APIResponseDto apiResponseDto = new APIResponseDto();
	    apiResponseDto.setProjectDto(projectDto);
	    apiResponseDto.setTaskList(taskDtoList);
	    
	    return List.of(apiResponseDto);
	}
	@Override
	public ProjectDto findByProjectName(String projectName) {
		Project project = repository.findByProjectName(projectName);
		return modelMapper.map(project,ProjectDto.class);
	}
	
	
	
	
	
	
	
	
	
}
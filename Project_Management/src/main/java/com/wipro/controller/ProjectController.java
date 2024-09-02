package com.wipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.dto.APIResponseDto;
import com.wipro.dto.ProjectDto;
import com.wipro.exception.ProjectIdNotFoundException;
import com.wipro.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/project/")
public class ProjectController {
	@Autowired
	private ProjectService service;
	
	@PostMapping("add")
	   public ProjectDto saveUser( @RequestBody  @Valid ProjectDto  projectdto)
	   {
		   return service.createUser(projectdto);
	   }
	   
	   @GetMapping("getall")
	   public List<ProjectDto> getAllUsers()
	   {
		   return service.getAllProjects();
	  }
	   @GetMapping("{pid}")
	   public List<APIResponseDto> getProjectById(@PathVariable("pid") Long pid) throws ProjectIdNotFoundException {
		   return service.getProjectById(pid);
	   }
	   
	   @GetMapping("get/{projectName}")
	   public ProjectDto getByProjectName(@PathVariable("projectName") String projectName) {
		   
		   return service.findByProjectName(projectName);
	   }
	  
	  
	   @PutMapping("update/{pid}")
	   public ProjectDto updateProjectById (@PathVariable("pid") Long x,@RequestBody @Valid  ProjectDto projectdto) throws ProjectIdNotFoundException
	   {
		   return service.updateProjectById(x,projectdto);
	   }
	   @DeleteMapping("{pid}")
	   public String deleteProjectById(@PathVariable("pid") Long x) {
		   service.deleteProjectById(x);
		   return "project deleted with id" +x;
		   

}
	  
	 
	  
}

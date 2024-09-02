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

import com.wipro.dto.TaskDto;
import com.wipro.exception.TaskIdNotFoundException;
import com.wipro.service.TaskService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/task/")
public class TaskController {
	@Autowired
	private TaskService taskservice;
	
	@PostMapping("{add}")
	public TaskDto save(@RequestBody @Valid TaskDto taskDto) {
		return taskservice.createTask(taskDto);
	}

	@GetMapping("{getall}")
	public List<TaskDto> getAll(){
		return taskservice.FindAll();
	}
	
	@GetMapping("get/{id}")
	public TaskDto getTaskById(@PathVariable("id") Long taskid) throws TaskIdNotFoundException {
		 return taskservice.getTaskById(taskid);
	 }
	@GetMapping("/getalltasksbypid/{pid}")
    public List<TaskDto> getAllTasksByPid(@PathVariable("pid") Long pid)
    {
    	return taskservice.getAllTasksByPid(pid);
    }
	 @PutMapping("{pid}")
	   public TaskDto updatetaskById(@PathVariable("pid") Long x,@RequestBody @Valid TaskDto taskdto) throws TaskIdNotFoundException
	   {
		   return taskservice.updatetaskById(x,taskdto);
	   }
	   @DeleteMapping("{pid}")
	   public String deleteTaskById(@PathVariable("pid") Long x) {
		   taskservice.deletetaskById(x);
		   return "project deleted with id" +x;

	
}
}

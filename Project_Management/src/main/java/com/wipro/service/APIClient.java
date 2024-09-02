package com.wipro.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.dto.TaskDto;

//@FeignClient(url="http://localhost:6064",name = "Task-Management")
@FeignClient(name="Task-Management")
public interface APIClient {
	
	@GetMapping("/api/task/getalltasksbypid/{pid}")
    public List<TaskDto> getAllTasksByPid(@PathVariable("pid") Long pid);

    

	

}

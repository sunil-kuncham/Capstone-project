package com.wipro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.dto.TaskDto;
import com.wipro.entity.Task;
import com.wipro.exception.TaskIdNotFoundException;
import com.wipro.repository.TaskRepository;


@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ModelMapper mp;

	@Override
	public TaskDto createTask(TaskDto taskDto) {
		Task task = mp.map(taskDto, Task.class);
		Task savedTask = taskRepository.save(task);
		return mp.map(savedTask, TaskDto.class);
	}

	
	

	@Override
	public List<TaskDto> FindAll() {
		List<Task> listTask = (List<Task>)taskRepository.findAll();
		List<TaskDto> listDto = new ArrayList<TaskDto>();
		for(Task t : listTask) {
			listDto.add(mp.map(t, TaskDto.class));
		}
		
		return listDto;
	}




	@Override
	public TaskDto getTaskById(Long taskid) throws TaskIdNotFoundException {
		Task task= taskRepository.findById(taskid).orElseThrow(()-> new TaskIdNotFoundException("Task id not found" +taskid));
		return mp.map(task, TaskDto.class);
	}




	@Override
	public List<TaskDto> getAllTasksByPid(Long pid) {
		List<Task> taskList = taskRepository.findAllByPid(pid);
		return taskList.stream().map(entity->mp.map(entity,TaskDto.class)).collect(Collectors.toList());
	}




	@Override
	public TaskDto updatetaskById(Long x, TaskDto taskdto) throws TaskIdNotFoundException {
		Task retrievedUser = taskRepository.findById(x).orElseThrow(()-> new TaskIdNotFoundException("Task id not found" +x));
		retrievedUser.setTaskname(taskdto.getTaskname());
		retrievedUser.setTaskDescription(taskdto.getTaskDescription());
		retrievedUser.setTaskStatus(taskdto.getTaskStatus());
		retrievedUser.setPid(taskdto.getPid());
		
		Task savedObject= taskRepository.save(retrievedUser);
		
		TaskDto dto = mp.map(savedObject,TaskDto.class);
		return dto;	
		
	}




	@Override
	public void deletetaskById(Long x) {
		taskRepository.deleteById(x);
	}









	
	
	
	



	

}

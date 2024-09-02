package com.wipro.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wipro.entity.Task;

@DataJpaTest
public class TaskRepositoryTests {
	@Autowired
	private TaskRepository repository;
	
	@Test
	@DisplayName("saving the Task Details")
	public void saveTasks() {
	Task task = Task.builder().pid((long)7).taskname("checklist").taskDescription("cheacking check list").taskStatus("completed").build();
	Task savedtask = repository.save(task);
	assertThat(savedtask).isNotNull();
	assertThat(savedtask.getPid()).isGreaterThan(0);
	}
	
	@Test
	@DisplayName("getting the Task Details")
	public void getProjects() {
		Task  t1 = Task.builder().pid((long)2).taskname("salary").taskDescription("cheaking salaries").taskStatus("completed").build();
		Task t2 = Task.builder().pid((long)2).taskname("hike").taskDescription("cheaking hikes").taskStatus("Inprogress").build();
		repository.save(t1);
		repository.save(t2);
		
		List<Task> plist =  (List<Task>) repository.findAll();
		assertThat(plist).isNotNull();
		assertThat(plist.size()).isEqualTo(2);
	}
	@Test
	@DisplayName("Getting the task details by using taskid")
	public void getProjectById() {
		Task task = Task.builder().pid((long)3).taskname("cart").taskDescription("creating cart").taskStatus("in-progress").build();
		Task savetask = repository.save(task);
		Task prj =repository.findById(savetask.getTaskid()).get();
		assertThat(prj).isNotNull();
		assertThat(prj.getTaskid()).isGreaterThan(0);
	}
	@Test
	@DisplayName("Getting the task details by using project id")
	public void getAllTasksByPid() {
		Task task = Task.builder().pid((long)1).taskname("MOVE").taskDescription("creating MOVE PAGE").taskStatus("in-progress").build();
		Task savetask = repository.save(task);
		List<Task> prj =repository.findAllByPid(savetask.getPid());
		assertThat(prj).isNotNull();
		
	}
}


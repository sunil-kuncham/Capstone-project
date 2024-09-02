package com.wipro;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wipro.entity.Project;
import com.wipro.repository.ProjectRepository;

@DataJpaTest
public class ProjectRepositoryTests {
	@Autowired
	private ProjectRepository repository;
	
	@Test
	@DisplayName("saving the project Details")
	public void saveProjects() {
	Project project = Project.builder().projectName("Work").projectDescription("manage work").startTime("05-22-2022").endTime("09-08-2023").build();
	Project savedProject = repository.save(project);
	assertThat(savedProject).isNotNull();
	assertThat(savedProject.getPid()).isGreaterThan(0);
	}
	
	@Test
	@DisplayName("getting the project Details")
	public void getProjects() {
		Project project1 = Project.builder().projectName("manager").projectDescription("manager work").startTime("08-22-2023").endTime("02-08-2024").build();
		Project project2 = Project.builder().projectName("Task").projectDescription("manage Task work").startTime("09-02-2022").endTime("10-08-2022").build();
		repository.save(project1);
		repository.save(project2);
		
		List<Project> plist =  (List<Project>) repository.findAll();
		assertThat(plist).isNotNull();
		assertThat(plist.size()).isEqualTo(2);
	}
	@Test
	@DisplayName("Getting the project details by using id")
	public void getProjectById() {
		Project project = Project.builder().projectName("performane").projectDescription("manage performance table").startTime("05-02-2022").endTime("19-08-2022").build();
		Project saveproject = repository.save(project);
		Project prj =repository.findById(saveproject.getPid()).get();
		assertThat(prj).isNotNull();
		assertThat(prj.getPid()).isGreaterThan(0);
	}
}

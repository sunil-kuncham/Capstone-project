package com.wipro.repository;






import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.Task;
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

	List<Task> findAllByPid(Long pid);

	
	

	

	

}

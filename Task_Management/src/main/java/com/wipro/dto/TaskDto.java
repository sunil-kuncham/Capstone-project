package com.wipro.dto;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

	
	 
	 private Long taskid;
	 @NotNull(message="project-id not null")
     private Long pid;
	 @NotEmpty(message="task id not null")
	 private String taskname;
	 @NotEmpty(message="taskDescription not be empty")
	 private String taskDescription;
	 @NotEmpty(message="task not be null")
	 private String taskStatus;

}




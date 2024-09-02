package com.wipro.dto;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
	@NotNull
    
	
	private Long taskid;
	@NotNull
    
	private Long pid;
	 @NotNull
	    @Size(min = 5, max = 100)
	private String taskname;
	 @NotNull
	    @Size(min = 5, max = 100)
	private String taskDescription;
	 @NotNull
	private String taskStatus;

}





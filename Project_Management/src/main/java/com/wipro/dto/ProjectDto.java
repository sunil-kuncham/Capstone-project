package com.wipro.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
	
   
	private Long pid;

    
	

   
    @NotEmpty(message = "Project name cannot be empty")
   
    private String projectName;

    
    @NotEmpty(message = "Project description cannot be empty")
   
    private String projectDescription;

    @NotEmpty
    private String startTime;

    @NotEmpty
    private String endTime;
    
	
	

}

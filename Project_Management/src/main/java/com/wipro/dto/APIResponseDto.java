package com.wipro.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponseDto {
private ProjectDto projectDto;
private TaskDto taskDto;
private List<TaskDto> taskList;
public APIResponseDto(ProjectDto projectDto,  List<TaskDto> taskList) {
	super();
	this.projectDto = projectDto;
	
	this.taskList = taskList;
}




}


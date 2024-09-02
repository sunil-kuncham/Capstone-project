package com.wipro.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	
	private String projectName;
	private String projectDescription;
	
	private String startTime;
	private String endTime;
	
	
	
	

}

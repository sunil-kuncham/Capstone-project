package com.wipro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDto {
	
    private Integer id;
    @NotEmpty
	@Email(message = "Email should be valid")
	private String email;
	 @NotEmpty(message = "Password cannot be empty")
	 @Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	 @NotEmpty(message = "Role cannot be empty")
	private String role;
	@NotEmpty(message = "First Name  cannot be empty")
	private String firstName;
	@NotEmpty(message = "Last Name  cannot be empty")
	private String lastName;
	
	
}
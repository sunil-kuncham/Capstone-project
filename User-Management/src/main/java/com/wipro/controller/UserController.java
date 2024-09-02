package com.wipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.dto.UserDto;
import com.wipro.exception.UserIdNotFoundException;
import com.wipro.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/add")
	public UserDto saveUser(@RequestBody @Valid UserDto userdto ) {
		userdto.setPassword(passwordEncoder.encode(userdto.getPassword()));
		return service.createUser(userdto);
	}
	
	@GetMapping("/getdetails")
	   public List<UserDto> getAllStudents() 
	   {
		   return service.getAll();
	   }
	 
	 @PutMapping("/update/{id}")
	 public UserDto update(@PathVariable("id") Integer x,@RequestBody @Valid UserDto userdto) throws UserIdNotFoundException
	   {
		 userdto.setPassword(passwordEncoder.encode(userdto.getPassword()));
		   return service.updateStudentById(x,userdto);
	   }
	 @DeleteMapping("/delete/{id}")
	 public String deleteById(@PathVariable("id") Integer x)
	   {
		   service.deleteById(x);
		   return "student deleted successfully with " + x +"id";
	   }
}

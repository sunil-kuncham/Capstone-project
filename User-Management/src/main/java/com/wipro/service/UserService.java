package com.wipro.service;

import java.util.List;

import com.wipro.dto.UserDto;
import com.wipro.exception.UserIdNotFoundException;

public interface UserService {

	public UserDto createUser(UserDto userdto);

	public List<UserDto> getAll() ;

	public UserDto updateStudentById(Integer x, UserDto userdto) throws UserIdNotFoundException;

	public void deleteById(Integer x);



	



}

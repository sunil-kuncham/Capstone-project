package com.wipro.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.wipro.dto.UserDto;
import com.wipro.entity.User;
import com.wipro.exception.DuplicateEmailException;
import com.wipro.exception.UserIdNotFoundException;
import com.wipro.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper mp;
	
	
	
	@Override
	public UserDto createUser(UserDto userdto) {
		try {
			User user = mp.map(userdto, User.class);
			User saveuser = repository.save(user);
			return mp.map(saveuser, UserDto.class);
		}
		catch (DataIntegrityViolationException e) {
			throw new DuplicateEmailException("Already registed with this Email. Try to login. " + userdto.getEmail());
		}
		
	}

	@Override
	public List<UserDto> getAll()  {
		List<User> stu =(List<User>)repository.findAll();
		List<UserDto> dto = new ArrayList<UserDto>(); 
		for(User st:stu) {
			dto.add(mp.map(st, UserDto.class));
		}
		return dto;
	}

	@Override
	public UserDto updateStudentById(Integer x, UserDto userdto) throws UserIdNotFoundException {
		User retrievedUser = repository.findById(x).orElseThrow(()-> new UserIdNotFoundException("User Not Found with is"+ x));
		
		retrievedUser.setEmail(userdto.getEmail());
		retrievedUser.setPassword(userdto.getPassword());
		retrievedUser.setPassword(userdto.getFirstName());
		retrievedUser.setPassword(userdto.getLastName());
		retrievedUser.setRole(userdto.getRole());
	    User stu =repository.save(retrievedUser);
		UserDto dto = mp.map(stu,UserDto.class);
		return dto;
	}

	@Override
	public void deleteById(Integer x) {
		repository.deleteById(x);
		
	}

	

	
	}


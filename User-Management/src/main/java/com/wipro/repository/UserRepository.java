package com.wipro.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.User;

@Repository	
public interface UserRepository extends CrudRepository<User, Integer> {
	public Optional<User> findByEmail(String username);
}

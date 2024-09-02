package com.wipro.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wipro.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails{
	private String name;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails(User user) {
		name = user.getEmail();
		password = user.getPassword();
		authorities = Arrays.stream(user.getRole().split(","))
							.map(SimpleGrantedAuthority :: new)
							.collect(Collectors.toList());
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		 
		return name;
	}
	
}
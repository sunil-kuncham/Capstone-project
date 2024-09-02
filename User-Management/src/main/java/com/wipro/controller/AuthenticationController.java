package com.wipro.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.dto.AuthRequest;
import com.wipro.jwt.JwtService;
import com.wipro.service.MyUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/authenticate") 
	public String authenticate(@RequestBody AuthRequest authRequest) throws Exception {

		Authentication authentication = authenticationManager
				.authenticate(
				new UsernamePasswordAuthenticationToken
				(authRequest.getUsername(), authRequest.getPassword()));

		if(authentication.isAuthenticated()) {
			return jwtService
					.generateToken
					(myUserDetailsService
							.loadUserByUsername(authRequest.getUsername()));
		}

		else {
			throw new UsernameNotFoundException("user not found" + authRequest.getUsername());
		}
	}
	
	@GetMapping("/validate/{token}")
	public String validateToken(@PathVariable("token") String token) {
		if(jwtService.isTokenValid(token)){ 
			return "Token Valid";
		}
		return "Token Invalid";
	}
}


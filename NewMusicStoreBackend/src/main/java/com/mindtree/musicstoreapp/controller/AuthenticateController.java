package com.mindtree.musicstoreapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.musicstoreapp.config.JWTUtil;
import com.mindtree.musicstoreapp.dto.UserDto;
import com.mindtree.musicstoreapp.entity.User;
import com.mindtree.musicstoreapp.exception.MainException;
import com.mindtree.musicstoreapp.model.JWTResponse;
import com.mindtree.musicstoreapp.serviceimpl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	JWTUtil jwtUtil;
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody UserDto userDto) throws MainException{
		
			authenticate(userDto.getUsername(), userDto.getPassword());
			
			
		String token=jwtUtil.generateToken(userDetailsServiceImpl.loadUserByUsername(userDto.getUsername())) ;
		System.out.println("token inside authentication Controoler "+token);
		return new ResponseEntity<String>(token,HttpStatus.ACCEPTED);
	}
	
	
	private void authenticate(String username,String password) throws MainException {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));	
		}
		catch (DisabledException e) {
			throw new MainException("User Disabled "+e.getMessage());
		}
		catch (BadCredentialsException e) {
			throw new MainException("Invalid Credentials "+e.getMessage());
		}
	}
	
	
	// returns the detail of current user
	@GetMapping("/currentUser")
	public User currentUser(Principal principal) {
		System.out.println("Inside Current User");
		System.out.println("Principal "+principal);
		return userDetailsServiceImpl.loadUserByUsername(principal.getName());
	}
}

package com.mindtree.musicstoreapp.serviceimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mindtree.musicstoreapp.entity.User;
import com.mindtree.musicstoreapp.repository.IUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	IUserRepository userRepository;
	
	@Override
	public User loadUserByUsername(String username) {
		
			return userRepository.findByUsername(username).map(obj->{
				return obj;
			}).orElseThrow(()-> new UsernameNotFoundException("Check username or password"));
		
		
	}

}

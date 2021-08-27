package com.mindtree.musicstoreapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

import com.mindtree.musicstoreapp.entity.Role;
import com.mindtree.musicstoreapp.entity.User;
import com.mindtree.musicstoreapp.serviceimpl.UserServiceImpl;

@SpringBootApplication
public class MusicStoreAppApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(MusicStoreAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Code Started");
		User user=new User("admin", bCryptPasswordEncoder.encode("admin"), "admin", "admin", "admin","1");
		Role role=new Role("admin");
		userServiceImpl.register(user, role);
	}

	

	

	
}

package com.mindtree.musicstoreapp.service;

import java.sql.SQLIntegrityConstraintViolationException;

import com.mindtree.musicstoreapp.dto.UserDto;
import com.mindtree.musicstoreapp.entity.Role;
import com.mindtree.musicstoreapp.entity.User;
import com.mindtree.musicstoreapp.exception.service.ServiceException;

public interface IUserService {

	String register(User user, Role role) throws ServiceException;

	String login(UserDto user) throws ServiceException;

	String giveAccess(String username) throws ServiceException;

	

	

}

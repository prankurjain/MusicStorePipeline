package com.mindtree.musicstoreapp.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.musicstoreapp.dto.ErrorHandler;
import com.mindtree.musicstoreapp.exception.MainException;

@RestControllerAdvice
public class MusicStoreHandler {

	@ExceptionHandler(MainException.class)
	public ResponseEntity<ErrorHandler> error(Exception e)
	{
		return new ResponseEntity<ErrorHandler>(new ErrorHandler(e.getMessage(),HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
	}
}

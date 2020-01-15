package com.spring.beerservice.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		List<String> errors = new ArrayList<String>(ex.getBindingResult().getFieldErrorCount());
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.add(error.getField() + " : " + error.getDefaultMessage());
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

}

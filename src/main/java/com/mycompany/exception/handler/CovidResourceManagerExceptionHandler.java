package com.mycompany.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mycompany.entity.CustomErrorResponse;
import com.mycompany.exception.IncorrectUserException;

@ControllerAdvice
public class CovidResourceManagerExceptionHandler {
	
	@ExceptionHandler
	public String handleException(IncorrectUserException exc) {
		CustomErrorResponse error = new CustomErrorResponse(
									HttpStatus.BAD_REQUEST.value(), exc.getMessage(), 
									System.currentTimeMillis());
		return "error";
	}

}

package com.mycompany.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mycompany.entity.CustomErrorResponse;
import com.mycompany.exception.IncorrectUserException;

@ControllerAdvice
public class CovidResourceManagerExceptionHandler {
	
	@ExceptionHandler
	public String handleException(IncorrectUserException exc, Model model) {
		CustomErrorResponse error = new CustomErrorResponse(
									HttpStatus.INTERNAL_SERVER_ERROR.value(), exc.getMessage(), 
									System.currentTimeMillis());
		
		model.addAttribute("error", error);
		
		return "error";
	}
	
	@ExceptionHandler
	public String handleException(NullPointerException exc, Model model) {
		CustomErrorResponse error = new CustomErrorResponse(
									HttpStatus.INTERNAL_SERVER_ERROR.value(), exc.getMessage(), 
									System.currentTimeMillis());
		
		model.addAttribute("error", error);
		
		return "error";
	}

}

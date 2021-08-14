package com.mycompany.exception.handler;

import javax.el.PropertyNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mycompany.entity.CustomErrorResponse;
import com.mycompany.exception.IncorrectUserException;

@ControllerAdvice
public class CovidResourceManagerExceptionHandler {
	
	@ExceptionHandler(value = {IncorrectUserException.class, NullPointerException.class,
			PropertyNotFoundException.class})
	public String handleException(Exception exc, Model model) {
		CustomErrorResponse error = new CustomErrorResponse(
									HttpStatus.INTERNAL_SERVER_ERROR.value(), exc.getMessage(), 
									System.currentTimeMillis());
		
		model.addAttribute("error", error);
		
		return "error";
	}

}

package com.mycompany.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.entity.User;
import com.mycompany.service.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
	}
	
	//URL:  http://localhost:8080/user/register
	
	@GetMapping(value = "/register")
	public String register(Model model) {
		User u = new User();
		
		model.addAttribute("user", u);
		return "signup";
	}
	
	@PostMapping(value = "/register")
	public String registerUser(Model model, @ModelAttribute User u, BindingResult results) {
		
		u.setAccountStatus("ACTIVE");
		u.setEnabled(1);
		
		//Needs to be printed in the logs 
		
		//System.out.println(u);  
		userService.addUser(u);
		
		return "redirect:/home";
	}

}

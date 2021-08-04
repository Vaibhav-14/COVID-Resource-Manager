package com.mycompany.controller;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.entity.Post;
import com.mycompany.entity.User;
import com.mycompany.service.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//URL:  http://localhost:8080/user/register
	
	@GetMapping(value = "/register")
	public String register(Model model) {
		
		User user = new User();
		user.setDateOfBirth(new Date());
		
		model.addAttribute("user", user);
		return "signup";
	}
	
	@PostMapping(value = "/register")
	public String registerUser(Model model, @Valid @ModelAttribute User user, BindingResult results) {
		
		List<String> usernameList = userService.getListOfAllUsernames();
		for(String currentUsername : usernameList)
			if(user.getUsername().equals(currentUsername)) {
				results.rejectValue("username", "error.user", "Username is already registered");
				break;
			}
		
	  int age = (int)((new Date().getTime() -  user.getDateOfBirth().getTime())/(1000l* 60 * 60 * 24 * 365));
	  if(age<18)
		  results.rejectValue("dateOfBirth", "error.user", "Must be at least 18 to register");
	  
	  if(!user.getPassword().equals(user.getRetypepassword()))
		  results.rejectValue("retypepassword", "error.user","Confirmed Password is not the same");
		
		if(results.hasErrors())
			return "signup";
		
		user.setAccountStatus("ACTIVE");
		user.setEnabled(1);
		
		//Needs to be printed in the logs 
		
		//System.out.println(u);  
		userService.addUser(user);
		
		return "redirect:/user/login";
	}

	@GetMapping(value = "/login")
	public String showLoginForm() {
		return "login";
	}
	
	@GetMapping(value = "/profile")
	public String displayProfile(@RequestParam(required = false) String username, Model model) {
		List<Post> posts = userService.displayProfile(username);
		User user = userService.getUser(username);
		model.addAttribute("username", username);
		model.addAttribute("user", user);
		model.addAttribute("posts", posts);
		return "profile";
	}
	
}

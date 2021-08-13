package com.mycompany.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.entity.Comment;
import com.mycompany.entity.Post;
import com.mycompany.entity.User;
import com.mycompany.service.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//URL:  http://localhost:8080/user/register
	
	private String createRedirectViewPath(String requestMapping) {
        StringBuilder redirectViewPath = new StringBuilder();
        redirectViewPath.append("redirect:");
        redirectViewPath.append(requestMapping);
        return redirectViewPath.toString();
    }
	
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
		
		user.setWarnings(0);
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
	public String displayProfile(@RequestParam(required=false) String username, Model model) {
		List<Post> posts = userService.displayProfile(username);
		User user;
		if (username != null)
			user = userService.getUserFromUsername(username);
		else
			user = userService.getLoggedInUser();
		model.addAttribute("IsUsername", username);
		model.addAttribute("username", user.getUsername());
		model.addAttribute("tag", null);
		model.addAttribute("user", user);
		model.addAttribute("posts", posts);
		model.addAttribute("comment", new Comment());
		return "profile";
	}
	
	@GetMapping(value = "/block/{username}")
	public String blockUser(@PathVariable String username) {
		User user = userService.getUserFromUsername(username);
		user.setEnabled(0);
		userService.updateUser(user);
		logger.info("Admin has suspended the account of " + username);
		return "redirect:/user/profile?username="+username;
		
	}
	
	@GetMapping(value = "/unblock/{username}")
	public String unblockUser(@PathVariable String username) {
		User user = userService.getUserFromUsername(username);
		user.setEnabled(1);
		userService.updateUser(user);
		logger.info("Admin has removed suspension on account of " + username);
		return "redirect:/user/profile?username="+username;
	}
	

	@PostMapping("/delete")
	public String deleteUserAccount(@RequestParam(name="username") String username)
	{
		userService.deleteUserAccount(username);	
		//return "redirect:/user/logout";
		return "redirect:/user/logout";
	}
	
	

	@GetMapping("/search")
	@ResponseBody
	public List<String> getUsersByKeyword(@RequestParam String term) {
		return userService.searchUsersByKeyWord(term);
	}
	
	@GetMapping("/update")
	public String updateProfile(Model model) throws Exception {
		User user = userService.getLoggedInUser();
		model.addAttribute("user", user);
		return "update-profile";
	}
	

	@PostMapping("/update") 
	public String updateProfile(@ModelAttribute("user") User user, Model model, BindingResult results) {
		if(results.hasErrors())
			return "update-profile";
		userService.updateUserProfile(user);
		return "redirect:/home";
	}
	
	@GetMapping("/checkPassword")
	public String enterOldPassword(Model model) {
		User user = userService.getLoggedInUser();
		user.setPassword(null);
		model.addAttribute("user", user);
		return "change-password-1";
	}
	
	@PostMapping("/checkPassword")
	public String checkOldPassword(Model model, @Valid @ModelAttribute("user") User user, BindingResult results) {
		User loggedInUser = userService.getLoggedInUser();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(!encoder.matches(user.getPassword(), loggedInUser.getPassword())) {
			results.rejectValue("password", "error.user", "Password Doesn't match");
		}
		if(results.hasErrors()) {
			return "change-password-1";
		}
		loggedInUser.setPassword(null);
		model.addAttribute("user",  loggedInUser);
		return "change-password-2";
	}
	
	@PostMapping("/changePassword")
	public String saveNewPassword(Model model, @Valid @ModelAttribute("user") User user, BindingResult results) {
		User loggedInUser = userService.getLoggedInUser();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(encoder.matches(user.getPassword(), loggedInUser.getPassword())) {
			results.rejectValue("password", "error.user", "New Password can't be same as Old Password");
		}
		if(!user.getPassword().equals(user.getRetypepassword())) 
			results.rejectValue("retypepassword", "error.user","Confirmed Password is not the same");
		if(results.hasErrors())
			return "change-password-2";
		user.setPassword(encoder.encode(user.getPassword()));
		userService.changePassword(user);
		System.out.println(user.getPassword());
		return "redirect:/user/profile";
	}

	
}

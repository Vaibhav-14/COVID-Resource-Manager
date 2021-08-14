package com.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.entity.Comment;
import com.mycompany.entity.User;
import com.mycompany.service.PostService;
import com.mycompany.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping({"/", "/home"})
	public String showHomePage(Model model, @RequestParam(defaultValue = "0") Integer pageNumber) {
		User user = userService.getLoggedInUser();

		String username;
		if(user == null) {
			username = null;
		}
		else {
			username = user.getUsername();
		}
		model.addAttribute("username", username);
		
		//posts fetched per page
		int pageSize=5;
		
		//sorted on the basis of dateTime attribute
		String sortBy= "dateTime";
		
		//retrieving results
		model.addAttribute("posts", postService.getAllPostsFromPageable(pageNumber, pageSize, sortBy));
		
		
		model.addAttribute("comment", new Comment());
		return "home";
	}
	
	@GetMapping("/autocomplete")
	public String getUsersByKeyword(@RequestParam String term) throws Exception {
		if (term.startsWith("@"))
			return "redirect:/user/search?term=" + term.substring(1);
		else if (term.startsWith("#"))
			return "redirect:/tag/search?term=" + term.substring(1);
		else
			throw new Exception();
	}
	
	@RequestMapping("/403")
	public String accessDenied() {
		return "403";
	}
	
}

package com.mycompany.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.entity.Comment;
import com.mycompany.entity.User;
import com.mycompany.service.CommentService;
import com.mycompany.service.PostService;
import com.mycompany.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping({"/", "/home"})
	public String showHomePage(Model model) {
		User user = userService.getUser(null);
		String username;
		if(user == null) {
			username = null;
		}
		else {
			username = user.getUsername();
		}
		model.addAttribute("username", username);
		model.addAttribute("posts", postService.getAllPost());
		model.addAttribute("comment",new Comment());
		return "home";
	}
	
	@GetMapping("/autocomplete")
	public String getUsersByKeyword(@RequestParam String term) throws Exception {
		if (term.startsWith("@"))
			return "redirect:/user/search?term=" + term.substring(1);
		else if (term.startsWith("#"))
			return "redirect:/tag/search?term=" + term;
		else
			throw new Exception();
	}
	
}

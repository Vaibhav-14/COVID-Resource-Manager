package com.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.service.PostService;

@Controller
public class HomeController {
	
	@Autowired
	private PostService postservice;
	
	@RequestMapping(value = {"/", "/home"})
	public String showHomePage(Model model) {
		model.addAttribute("posts", postservice.getAllPost());
		return "home";
	}
}

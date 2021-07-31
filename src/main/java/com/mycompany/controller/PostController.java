package com.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mycompany.entity.Post;
import com.mycompany.service.PostService;


@Controller
public class PostController {
	
	@Autowired
	private PostService postservice;
	
	@GetMapping("//{username}/addpost")
	public String addPost(Model model) {
		Post post = new Post();
		model.addAttribute("post", post);
		return "addpost";
	}
	
	
	@PostMapping("/{username}/addpost")
	public String savePost(@ModelAttribute("post") Post post, @PathVariable String username, Model model, BindingResult rs) {
		postservice.addPost(post, username);
		return "redirect:/home";
	}

}

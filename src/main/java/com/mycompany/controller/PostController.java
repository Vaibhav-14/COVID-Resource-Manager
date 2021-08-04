package com.mycompany.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.entity.Post;
import com.mycompany.service.PostService;


@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
		
	@GetMapping("/create")
	public String createPost(Model model) {
		Post post = new Post();
		model.addAttribute("post", post);
		return "create-post";
	}
	
	
	@PostMapping("/create")
	public String savePost(@ModelAttribute("post") Post post) {
		postService.addPost(post);
		return "redirect:/home";
	}
	
	@GetMapping("/update/{id}")
	public String updatePost(@PathVariable int id, Model model) {
		
		Post post = postService.getPostById(id);
		model.addAttribute("post", post);
		return "update-post";
	}
	
	@PostMapping("/update/{id}") 
	public String updatePost(@ModelAttribute("post") Post post, Model model) {
		postService.updatePost(post);
		return "redirect:/home";
	}
	
	
	@PostMapping("/searchresult")
	public String searchPostResult(Model model, @RequestParam(name = "searchentry") String searchEntry) {
		
		Set<Post> searchList = postService.getPostOnSearch(searchEntry);
		
		if (searchEntry.startsWith("#"))
			model.addAttribute("tag", searchEntry);
		else
			model.addAttribute("tag", null);
		model.addAttribute("username", searchEntry);
		model.addAttribute("IsUsername", null);
		model.addAttribute("user", null);
	    model.addAttribute("posts", searchList);
		return "profile";	
	}


}

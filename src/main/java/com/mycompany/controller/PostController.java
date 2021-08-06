package com.mycompany.controller;

import java.security.ProviderException;
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

import com.mycompany.entity.Comment;
import com.mycompany.entity.Post;
import com.mycompany.entity.User;

import com.mycompany.exception.IncorrectUserException;

import com.mycompany.service.PostService;
import com.mycompany.service.UserService;


@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
		
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
	public String updatePost(@PathVariable int id, Model model) throws ProviderException {
		
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
		User user = null;
		if (searchEntry.startsWith("#")) {
			model.addAttribute("tag", searchEntry);
		}
		else {
			user = userService.getUser(searchEntry);
			model.addAttribute("tag", null);
		}
		if( userService.getUser(null) == null) {
			model.addAttribute("isLoggedIn", false);
		}
		else {
			model.addAttribute("isLoggedIn", true);
		}
		model.addAttribute("comment", new Comment());
		model.addAttribute("username", searchEntry);
		model.addAttribute("IsUsername", null);
		model.addAttribute("user", user);
		

		Set<Post> searchList = postService.getPostOnSearch(searchEntry);
		model.addAttribute("posts", searchList);
		return "profile";	
	}
	
	@GetMapping("/delete/{id}")
	public String deletePosts(@PathVariable int id) throws IncorrectUserException {
		postService.deletePost(id);
		return "redirect:/";
	}


}

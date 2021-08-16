package com.mycompany.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.entity.Comment;
import com.mycompany.service.CommentService;


@Controller
@RequestMapping(value = "comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/create")
	public String saveComment(@Valid @ModelAttribute("comment") Comment comment, BindingResult result) {
		if(result.hasErrors()) 
			return "redirect:/";
		commentService.addComment(comment);
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String deleteComment(@ModelAttribute("comment") Comment comment) {
		commentService.deleteComment(comment.getId());
		return "redirect:/";
	}

}

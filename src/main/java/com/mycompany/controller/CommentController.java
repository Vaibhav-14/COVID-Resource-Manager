package com.mycompany.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.entity.Comment;
import com.mycompany.service.CommentService;

import io.micrometer.core.ipc.http.HttpSender.Request;

@Controller
@RequestMapping(value = "comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create")
	public String saveComment(@ModelAttribute("comment") Comment comment) {
		System.out.println(comment);
		commentService.addComment(comment);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteComment(@PathVariable int id) {
		commentService.deleteComment(id);
		return "redirect:/";
	}

}

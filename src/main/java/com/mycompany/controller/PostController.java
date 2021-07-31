package com.mycompany.controller;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mycompany.entity.Post;
import com.mycompany.entity.PostTag;
import com.mycompany.entity.Tag;
import com.mycompany.service.PostService;
import com.mycompany.service.TagService;
import com.mycompany.service.UserService;

@Controller
public class PostController {
	
	@Autowired
	private PostService postservice;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private TagService tagservice;
	
	@GetMapping("/{username}/addpost")
	public String addPost(Model model) {
		PostTag p = new PostTag();
		model.addAttribute("postform", p);
		return "addpost";
	}
	
	@PostMapping("/{username}/addpost")
	public String savePost(@ModelAttribute("postform") PostTag p, @PathVariable String username, Model model, BindingResult rs) {

		Post post = p.getPost();
		post.setUser(userservice.getUserFromUsername(username));
		post.setDateTime(new Timestamp(System.currentTimeMillis()));
		Set<Tag> tags = new HashSet<Tag>();
		Iterable<Tag> db_tags = tagservice.getAllTags();
		for (String s : p.getTags().split(",")) {
			int flag = 0;
			for (Tag tag : db_tags) {
				if(tag.getName().equals(s)) {
					flag = 1;
					tags.add(tag);
					break;
				}
			}
			if(flag == 0) {
				Tag tag = new Tag();
				tag.setName(s);
				Set<Post> tag_post = new HashSet<Post>();
				tag_post.add(post);
				tags.add(tag);
			}
		}
		//System.out.println(db_tags);
		post.setTags(tags);
		//System.out.println(post);
		postservice.addPost(post);
		return "redirect:/"+username+"/addpost";
	}

}

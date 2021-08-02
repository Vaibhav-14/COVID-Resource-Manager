package com.mycompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.service.TagService;

@Controller
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@GetMapping("/tags")
	@ResponseBody
	public List<String> getTagsByKeyword(@RequestParam String term) {
		return tagService.searchTagsByKeyWord(term);
	}
	
	@GetMapping("/tag/search")
	public String tagPage() {
		return "search-tag";
	}

}

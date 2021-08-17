package com.mycompany.controller;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.mycompany.entity.Tag;

import com.mycompany.dao.ITagFunctionDAO;
import com.mycompany.entity.Tag;
import com.mycompany.service.TagService;

@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTest {
	
	@Autowired
	private TagController tagController ; 
	@Autowired
	private MockMvc mockMvc;
	@Autowired 
	private TagService tagService;
	
	@Mock
	private Model model;
	
	@BeforeEach
	public void setupAuthentication(){
	    SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("GUEST","USERNAME", AuthorityUtils.createAuthorityList("USER", "ADMIN")));
	}
	
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(tagController).isNotNull();
	}
	
	@Test
	public void TagCreateTest() throws Exception {
		Tag tag = new Tag();
		tag.setName("vaccine");
		tag.setPosts(null);
		assertEquals("create-tag", tagController.addTags(model));
		assertEquals("redirect:/tag/create", tagController.saveTags(tag));
		tagController.getTagsByKeyword("vaccine") ; 

	}
	
	
	@Test
	public void deleteTagTest() {
		Tag tag = new Tag();
		tag.setName("Available");
		tagService.deleteTag(tag);
		
		
	}

}
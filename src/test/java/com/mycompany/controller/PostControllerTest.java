package com.mycompany.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {
	
	@Autowired
	private PostController postController ; 
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(postController).isNotNull();
	}
	
	@Test
	@Transactional
	public void validHTTPResponse() throws Exception{
		this.mockMvc.perform(get("/post/create")).andExpect(status().is3xxRedirection());
		this.mockMvc.perform(get("/post/update/1")).andExpect(status().is3xxRedirection());
//		this.mockMvc.perform(get("/post/search")).andExpect(status().isOk());
//		this.mockMvc.perform(get("/post/searchResult")).andExpect(status().isOk());
		this.mockMvc.perform(post("/post/update/1")).andExpect(status().isOk());
		this.mockMvc.perform(post("/post/create")).andExpect(status().isOk());
	}

}

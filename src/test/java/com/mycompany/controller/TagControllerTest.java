package com.mycompany.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTest {
	
	@Autowired
	private TagController tagController ; 
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(tagController).isNotNull();
	}
	
	@Test
	@Transactional
	public void validHTTPResponse() throws Exception{
		this.mockMvc.perform(get("/tags")).andExpect(status().isOk());
	}

}

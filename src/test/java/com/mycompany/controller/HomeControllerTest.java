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
import org.springframework.ui.Model;

import com.mycompany.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {
	
	@Autowired
	private HomeController homeController ; 
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(homeController).isNotNull();
	}
	
	@Test
	@Transactional
	public void validHTTPResponse() throws Exception{
		this.mockMvc.perform(get("/home")).andExpect(status().isOk());
	}
	
	@Test
	public void testshowHomePage(Model model) throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	
	

}
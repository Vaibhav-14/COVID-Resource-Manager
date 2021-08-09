package com.mycompany.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class UserControllerTest {
	
	@Autowired
	private UserController userController ; 
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(userController).isNotNull();
	}
	
	@Test
	@Transactional
	public void testshowLoginForm() throws Exception {
		this.mockMvc.perform(get("/user/login")).andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void testshowRegisterForm() throws Exception {
		this.mockMvc.perform(get("/user/register")).andExpect(status().isOk());
	}
	
}

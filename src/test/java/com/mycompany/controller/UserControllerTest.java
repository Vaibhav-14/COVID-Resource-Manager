package com.mycompany.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.springframework.security.core.authority.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.mycompany.entity.User;
import com.mycompany.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private UserController userController ; 
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setupAuthentication(){
	    SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("GUEST","USERNAME", AuthorityUtils.createAuthorityList("USER", "ADMIN")));
	}
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(userController).isNotNull();
	}
	
	@Test
	public void userControllerTest() throws Exception {
	
		//when(userService.updateUserProfile(user)).thenReturn(user);
		
		mockMvc.perform(get("/user/register")).andExpect(status().isOk()).andExpect(view().name("signup"));
		
		mockMvc.perform(get("/user/login")).andExpect(status().isOk()).andExpect(view().name("login"));
	}

	@Test
	public void user1ControllerTest() throws Exception {
	
		//when(userService.updateUserProfile(user)).thenReturn(user);
		
		
		mockMvc.perform(get("/user/update/{username}", "USERNAME")).andExpect(status().isOk()).andExpect(view().name("update-profile"));
		
	}
}
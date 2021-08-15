package com.mycompany.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.mycompany.service.TagService;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationControllerTest {
	
	@Autowired
	private NotificationController notifController ;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private Model model;
	
	@BeforeEach
	public void setupAuthentication(){
	    SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("GUEST","USERNAME", AuthorityUtils.createAuthorityList("USER", "ADMIN")));
	}
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(notifController).isNotNull();
	}
	
	@Test
	public void userControllerTest1() throws Exception {
		
//		assertEquals("notifications", notifController.showNotifications(model));
//		
//		assertEquals("redirect:/post/1", notifController.showPost(1));

		assertDoesNotThrow(() -> notifController.showPost(1));
	}

}

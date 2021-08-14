package com.mycompany.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;


import com.mycompany.entity.User;
import com.mycompany.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {
	
	@Autowired
	private HomeController homeController ; 
	@Autowired
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(homeController).isNotNull();
	}
	
	@Test
	@Transactional
	public void validHTTPResponse() throws Exception{
		User user = new User() ; 
		user.setId(1);
		user.setUsername("Champ");
		user.setEmail("Champ@gmail.com");
		user.setFirstname("Champ");
		user.setLastname("OK");
		user.setPassword("Thor");
		user.setMobile("1123456789") ; 
		user.setWarnings(0);
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date parsedDate = dateFormat.parse(String.valueOf("2000-01-01"));
		    user.setDateOfBirth(parsedDate);
		} catch(Exception e) { 
			System.out.println("Error : In Allocation of DOB to user");
			e.printStackTrace();
		}
		user.setGender("male");
		user.setEnabled(1);
		
		// Saving User in Database 
		assertDoesNotThrow(() -> userService.addUser(user));
		
		this.mockMvc.perform(get("/home"))
		.andExpect(status().isOk()) ;
		
		// User Authentication
				UsernamePasswordAuthenticationToken authReq
							      = new UsernamePasswordAuthenticationToken("Champ", "Thor");
				AuthenticationManager auth = new AuthenticationManager() {
											
						@Override
						public Authentication authenticate(Authentication authentication) throws AuthenticationException {
								return authentication;
						}
				};
				SecurityContext sc = SecurityContextHolder.getContext();
				sc.setAuthentication(auth.authenticate(authReq));
				
				this.mockMvc.perform(get("/home"))
				.andExpect(status().isOk()) ;
				
				
	}
	
//	@Test
//	public void testshowHomePage(Model model) throws Exception {
//		this.mockMvc.perform(get("/")).andExpect(status().isOk());
//	}
	
	@Test
	public void getUsersByKeywordTest() throws Exception {
		assertTrue(homeController.getUsersByKeyword("@").equals("redirect:/user/search?term="));
		assertTrue(homeController.getUsersByKeyword("#").equals("redirect:/tag/search?term="));
		assertThrows(Exception.class, () -> homeController.getUsersByKeyword("?"));
	}

	
	

}
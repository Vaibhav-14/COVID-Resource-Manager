package com.mycompany.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import org.springframework.security.core.authority.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.ui.Model;

import com.mycompany.entity.Post;
import com.mycompany.entity.User;
import com.mycompany.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private UserController userController ; 
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserService userService;
	
	@Mock
	Model model;
	
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
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	public void registerUserTest() throws Exception {
		User user = new User() ; 
		user.setId(1);
		user.setUsername("Champ");
		user.setEmail("Champ@gmail.com");
		user.setFirstname("Champ");
		user.setLastname("OK");
		user.setPassword("1123456789");
		user.setMobile("1123456789") ; 
		user.setWarnings(0);
		
		
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date parsedDate = dateFormat.parse(String.valueOf("2020-01-01"));
		    user.setDateOfBirth(parsedDate);
		} catch(Exception e) { 
			System.out.println("Error : In Allocation of DOB to user");
			e.printStackTrace();
		}
		user.setGender("male");
		user.setEnabled(1);
		MockHttpServletRequestBuilder request = post("/user/register").flashAttr("user", user) ;
		this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk()) ; 
		
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date parsedDate = dateFormat.parse(String.valueOf("1999-01-01"));
		    user.setDateOfBirth(parsedDate);
		} catch(Exception e) { 
			System.out.println("Error : In Allocation of DOB to user");
			e.printStackTrace();
		}
		request = post("/user/register").flashAttr("user", user) ;
		this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk()) ; 
		
		user.setRetypepassword("1123456789");
		request = post("/user/register").flashAttr("user", user) ;
		this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk()) ; 
		
		// Display Profile 
		request = get("/user/profile").param("username", user.getUsername()) ;
		this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk()) ; 
		
		// Block User 
		// User Authentication
		UsernamePasswordAuthenticationToken authReq
					      = new UsernamePasswordAuthenticationToken("Champ", "1123456789");
		AuthenticationManager auth = new AuthenticationManager() {
									
				@Override
				public Authentication authenticate(Authentication authentication) throws AuthenticationException {
						return authentication;
				}
		};
							    
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth.authenticate(authReq));
		userController.blockUser("Champ") ; 
		
		// UnBlock User
		userController.unblockUser("Champ") ; 
		
		// Search User by Keyword
		userController.getUsersByKeyword("Champ") ;
		
		// Update User
		request = post("/user/update") ;
		this.mockMvc.perform(request).andDo(print()).andExpect(status().is3xxRedirection()) ; 
		
		//Update User 
		request = post("/user/update").flashAttr("user",  user) ;
		this.mockMvc.perform(request).andDo(print()).andExpect(status().is3xxRedirection()) ; 
		
		// Delete User Account 
		// User Authentication
				authReq
							      = new UsernamePasswordAuthenticationToken("Champ", "1123456789");
				auth = new AuthenticationManager() {
											
						@Override
						public Authentication authenticate(Authentication authentication) throws AuthenticationException {
								return authentication;
						}
				};
									    
				sc = SecurityContextHolder.getContext();
				sc.setAuthentication(auth.authenticate(authReq));
		userController.deleteUserAccount("Champ") ; 
	}
	
	
	
}
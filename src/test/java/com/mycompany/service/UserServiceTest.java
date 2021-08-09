package com.mycompany.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mycompany.entity.Post;
import com.mycompany.entity.User;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	@Order(1)
	public void contextLoads() {
		assertThat(userService).isNotNull() ; 
	}
	
	@Test
	@Order(2)
	public void addUser() {
		User user = new User() ; 
		user.setId(1);
		user.setUsername("Champ");
		user.setEmail("Champ@gmail.com");
		user.setFirstname("Champ");
		user.setLastname("OK");
		user.setPassword("Thor");
		user.setMobile("1123456789") ; 
		user.setAccountStatus("active");
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
	}
	
	@Test
	@Order(3)
	public void displayProfile() {
		assertTrue(userService.displayProfile("Champ").size() >= 0 );
	}
	
	@Test
	@Order(4)
	public void getUser() {
		assertEquals(userService.getUser("Champ").getId() , 1);
	}
	
	@Test
	@Order(5)
	public void displayProfileTest() {
		List<Post> posts = userService.displayProfile("Champ") ; 
		assertTrue(posts.size() >= 0 );
		posts = userService.displayProfile(null) ; 
		assertTrue(posts.size() >= 0 );
	}
	
	@Test
	@Order(6)
	public void getUsersFromStringTest() {
		Set<User> users = userService.getUsersFromString("@Champ") ; 
		assertTrue(users.size() >= 1 );
	}
	
	@Test
	@Order(7)
	public void getListOfAllUsernamesTest() {
		assertTrue(userService.getListOfAllUsernames().size() >= 1 ) ; 
	}
	
	@Test
	@Order(8)
	public void updatUserTest() {
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
		User user = userService.getUser("Champ") ; 
		assertDoesNotThrow(() -> userService.updateUser(user));
	}
	
	@Test
	@Order(9)
	public void testGetterSetter() {
		assertDoesNotThrow(() -> userService.setUserDao(userService.getUserDao()));
	}

}

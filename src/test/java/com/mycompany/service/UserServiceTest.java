package com.mycompany.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mycompany.entity.User;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	@Order(1)
	public void contextLoads() {
		
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

}

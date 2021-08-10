package com.mycompany.entity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

@SpringBootTest
public class UserEntityTest {
	
	@Test
	public void usernameTest() {
		User user = new User();
		String username = "Champ";
		user.setUsername("Champ");
		String result= user.getUsername();
		assertEquals(result, username);
	}
	
	@Test
	public void mobileTest() {
		User user = new User();
		String mobile = "1234567890";
		user.setMobile(mobile);
		String result= user.getMobile();
		assertEquals(result, mobile);
	}
	
	@Test
	public void genderTest() {
		User user = new User();
		String gender = "M";
		user.setGender(gender);
		String result= user.getGender();
		assertEquals(result, gender);
	}

	@Test
	public void emailTest() {
		User user = new User();
		String email = "abc@abc.com";
		user.setEmail(email);
		String result= user.getEmail();
		assertEquals(result, email);
	}
	
	@Test
	public void NameTest() {
		User user = new User();
		String firstname = "abc";
		user.setFirstname(firstname);
		String result= user.getFirstname();
		assertEquals(result, firstname);
		String lastname = "xyz";
		user.setLastname(lastname);
		String result1= user.getLastname();
		assertEquals(result1, lastname);
	}
	
	@Test
	public void passTest() {
		User user = new User();
		String retypepassword = "abcd";
		user.setRetypepassword(retypepassword);
		String result= user.getRetypepassword();
		assertEquals(result, retypepassword);
	}
	
	@Test
	public void enabledTest() {
		User user = new User();
		int enabled = 1;
		user.setEnabled(1);
		int result= user.getEnabled();
		assertEquals(result, enabled);
	}
	
	@Test
	public void testGetterSetter() {
		User user = new User();
		assertDoesNotThrow(() -> user.setWarnings(user.getWarnings()));
		assertDoesNotThrow(() -> user.setDateOfBirth(user.getDateOfBirth()));
		assertDoesNotThrow(() -> user.setPosts(user.getPosts()));
		assertDoesNotThrow(() -> user.setComments(user.getComments()));
		assertDoesNotThrow(() -> user.setNotifications(user.getNotifications()));
		
	}
}

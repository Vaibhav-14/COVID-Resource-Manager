package com.mycompany.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import com.mycompany.entity.User;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MyUserDetailTest {
	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void userDetailTest() {
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
		MyUserDetails userDetail = new MyUserDetails(user) ; 
		userDetail.getAuthorities() ; 
		userDetail.getPassword() ; 
		userDetail.getUsername() ; 
		userDetail.isAccountNonExpired() ; 
		userDetail.isAccountNonLocked() ; 
		userDetail.isCredentialsNonExpired() ; 
		userDetail.isEnabled() ; 
	}
}

package com.mycompany.entity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Role_Tag_EntityTest {

	@Test
	public void testGetterSetter() {
		Role role = new Role();
		assertDoesNotThrow(() -> role.setId(role.getId()));
		assertDoesNotThrow(() -> role.setRole(role.getRole()));
		
		Tag tag = new Tag();
		assertDoesNotThrow(() -> tag.setPosts(tag.getPosts()));
		assertDoesNotThrow(() -> tag.setName(tag.getName()));
		
		Role role1 = new Role("Hello") ; 
	}
	
	
	
}

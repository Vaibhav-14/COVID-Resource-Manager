package com.mycompany.entity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostEntityTest {
	
	@Test
	public void testGetterSetter() {
		Post post = new Post();
		assertDoesNotThrow(() -> post.setType(post.getType()));
		assertDoesNotThrow(() -> post.setId(post.getId()));
		assertDoesNotThrow(() -> post.setComments(post.getComments()));
		
		post.equals(post) ; 
	}

}

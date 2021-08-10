package com.mycompany.entity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentEntityTest {
	
	Comment comment = new Comment();
	
	@Test
	public void testGetterSetter() {
		User user = new User();
		assertDoesNotThrow(() -> comment.setContent(comment.getContent()));
		assertDoesNotThrow(() -> comment.setDateTime(comment.getDateTime()));
		assertDoesNotThrow(() -> comment.setId(comment.getId()));
		assertDoesNotThrow(() -> comment.setPost(comment.getPost()));
		assertDoesNotThrow(() -> comment.setUser(comment.getUser()));
		

	}
}

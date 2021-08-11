package com.mycompany.entity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentEntityTest {
	
	Comment comment = new Comment();
	
	@Test
	public void testGetterSetter() throws ParseException {
		User user = new User();
		assertDoesNotThrow(() -> comment.setContent(comment.getContent()));
		assertDoesNotThrow(() -> comment.setDateTime(comment.getDateTime()));
		assertDoesNotThrow(() -> comment.setId(comment.getId()));
		assertDoesNotThrow(() -> comment.setPost(comment.getPost()));
		assertDoesNotThrow(() -> comment.setUser(comment.getUser()));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    Date parsedDate = dateFormat.parse("2000-01-01 00:00:01");
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		comment = new Comment("ok" , timestamp ) ; 
	}
}

package com.mycompany.entity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomErrorResponseTest {
	
	@Test
	public void testGetterSetter() {
		CustomErrorResponse error = new CustomErrorResponse();
		
		assertDoesNotThrow(() -> error.setMessage(error.getMessage()));
		assertDoesNotThrow(() -> error.setStatus(error.getStatus()));
		assertDoesNotThrow(() -> error.setTimeStamp(error.getTimeStamp()));
		
		CustomErrorResponse error1 = new CustomErrorResponse(1 , "message" , Long.parseLong("1"));

	}
}

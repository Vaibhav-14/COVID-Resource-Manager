package com.mycompany.entity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NotificationEntityTest {
	
	Notification notification = new Notification();
	
	@Test
	public void testGetterSetter() {
		User user = new User();
		assertDoesNotThrow(() -> notification.setActivityType(notification.getActivityType()));
		assertDoesNotThrow(() -> notification.setId(notification.getId()));
		assertDoesNotThrow(() -> notification.setIsUnread(notification.getIsUnread()));
		assertDoesNotThrow(() -> notification.setObjectType(notification.getObjectType()));
		assertDoesNotThrow(() -> notification.setObjectURL(notification.getObjectURL()));
		assertDoesNotThrow(() -> notification.setReceiver(notification.getReceiver()));
		assertDoesNotThrow(() -> notification.setSenderId(notification.getSenderId()));
		assertDoesNotThrow(() -> notification.setTimeSent(notification.getTimeSent()));
		
	}
}

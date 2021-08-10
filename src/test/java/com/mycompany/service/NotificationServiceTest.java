package com.mycompany.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mycompany.dao.INotificationDAO;
import com.mycompany.dao.ITagFunctionDAO;
import com.mycompany.entity.Notification;
import com.mycompany.entity.Post;
import com.mycompany.entity.Tag;
import com.mycompany.entity.User;

@SpringBootTest
public class NotificationServiceTest {
	@Autowired
	private INotificationDAO notificationDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServletContext context;
	
	@Test
	public void contextLoads() {
		assertThat(notificationDao).isNotNull(); 
		assertThat(userService).isNotNull();
		assertThat(context).isNotNull();
	}
	
	@Test
	public void saveTest() throws Exception {
		Notification notification = new Notification();
		notification.setActivityType("activity");
		notification.setId(1);
		notification.setIsUnread(1);
		notification.setObjectType("object");
		notification.setObjectURL("objectURL");
		User user= new User("a","b","abc","abc@test.com","12345abcd","7410185945","ACTIVE",new SimpleDateFormat("dd/MM/yyyy").parse("12/04/2010"),"male",0);
		notification.setReceiver(user);
		notification.setSenderId(123);
		Timestamp timeSent = new Timestamp(2);
		notification.setTimeSent(timeSent);
		notificationDao.save(notification);
		
		assertNotNull(notificationDao);
		
	}
	
	@Test
	public void getNotificationsTest() throws ParseException{
		User user= new User("a","b","abc","abc@test.com","12345abcd","7410185945","ACTIVE",new SimpleDateFormat("dd/MM/yyyy").parse("12/04/2010"),"male",0);
	    
		List<Notification> notificationsList = notificationDao.getAllNotificationsByReceiver(user);
		List<Notification> expectedList = new ArrayList<Notification>();//expecting NULL
		
		assertEquals(expectedList, notificationsList);
		
		
	}
	
	@Test
	public void deleteNotificationTest() {
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
		
		Notification notification = notificationDao.findById(1).get();
		notificationDao.deleteById(notification.getId());
		//assertNull(notification);
		
		
	}
	
	@Test
	public void setIdTest() {
		Notification notification = new Notification();
		notification.setId(2);
		assertTrue(notification.getId() == 2);
	}
	
	@Test
	public void setSenderIdTest() {
		Notification notification = new Notification();
		notification.setSenderId(11);
		assertTrue(notification.getSenderId() == 11);
	}
	
	@Test
	public void setActivityTypeTest() {
		String activityType = "activity";
		Notification notification = new Notification();
		notification.setActivityType(activityType);
		assertThat(notification.getActivityType() == "activity");
		
	}
	
	@Test
	public void setObjectTypeTest() {
		String objectType = "object";
		Notification notification = new Notification();
		notification.setObjectType(objectType);
		assertThat(notification.getObjectType() == "object");
	}
	
	@Test
	public void setObjectURLTest() {
		String objectURL = "objectURL";
		Notification notification = new Notification();
		notification.setObjectType(objectURL);
		assertThat(notification.getObjectURL() == "objectURL");
	}
	
	@Test
	
	public void setTimeSentTest() {
		Timestamp timeSent = new Timestamp(2);
		Notification notification = new Notification();
		notification.setTimeSent(timeSent);
		assertNotNull(notification.getTimeSent());
		
	}
	
	@Test
	public void setIsUnreadTest() {
		Notification notification = new Notification();
		notification.setIsUnread(1);
		assertThat(notification.getIsUnread() == 1);
	}
	
	
	

}

package com.mycompany.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.Notification;
import com.mycompany.entity.Post;
import com.mycompany.entity.Tag;
import com.mycompany.entity.User;

@SpringBootTest
public class NotificationServiceTest {
	@Autowired
	private INotificationDAO notificationDao;
	
	@Autowired
	private NotificationService notificationService ; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IUserFunctionDAO userDao;
	
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
		
		// Saving User in Database 
		assertDoesNotThrow(() -> userService.addUser(user));	
		// Reciever Part 
		user.setUsername("Thor");
		// Saving Reciever in Database 
		assertDoesNotThrow(() -> userService.addUser(user));	
		Timestamp timeSent = new Timestamp(2);
		notification.setTimeSent(timeSent);
		notificationService.save(notification);
		
	}
	
	@Test
	public void getNotificationsTest() throws ParseException{
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
				
		User user= userService.getUserFromUsername("Champ");
		List<Notification> notifications = notificationService.getNotifications() ; 
		
		
	}
	
	@Test
	public void deleteNotificationTest() {
		// User Authentication
		UsernamePasswordAuthenticationToken authReq
					      = new UsernamePasswordAuthenticationToken("Thor", "Thor");
		AuthenticationManager auth = new AuthenticationManager() {
							
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
					return authentication;
			}
		};
					    
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth.authenticate(authReq));
		
		List<Notification> notifications = notificationService.getNotifications() ; 
		for(Notification notification : notifications) {
			notificationService.deleteNotification(notification.getId());
		}
		
		// Delete User Account
		userService.deleteUserAccount("Thor");
		User user = userService.getUserFromUsername("Thor") ; 
		if (user != null) {
			userDao.delete(user);
		}
		
	}
	
	@Test
	public void removeUser() {
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
				
				// Delete User Account
				userService.deleteUserAccount("Champ");
				User user = userService.getUserFromUsername("Champ") ; 
				if (user != null) {
					userDao.delete(user);
				}
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
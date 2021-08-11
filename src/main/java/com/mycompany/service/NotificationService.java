package com.mycompany.service;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.INotificationDAO;
import com.mycompany.entity.Notification;
import com.mycompany.entity.User;
import com.mycompany.exception.IncorrectUserException;

@Service
@Transactional
public class NotificationService {
	
	@Autowired
	private INotificationDAO notificationDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServletContext context;

	public void save(Notification notification) {
		notificationDao.save(notification);
	}

	public void saveNotification(User sender, String activityType, String objectType,
						String objectURL, Set<User> receivers) {
		for (User user : receivers) {
			Notification notification = new Notification(sender.getId(), activityType, objectType, context.getContextPath() + objectURL);
			notification.setReceiver(user);
			save(notification);
		}
	}

	public void saveNotification(User sender, String activityType, 
			String objectType, String objectURL, User receiver) {
		Notification notification;
		if (sender != null)
			notification = new Notification(sender.getId(), activityType, objectType, objectURL);
		else
			notification = new Notification(null, activityType, objectType, context.getContextPath() + objectURL);
		notification.setReceiver(receiver);
		save(notification);
	}
	
	public List<Notification> getNotifications() {
		User user = userService.getLoggedInUser();

		List<Notification> notifications = notificationDao.getAllNotificationsByReceiver(user);
		return notifications;
	}

	public void deleteNotification(int id) throws IncorrectUserException {
		User user = userService.getLoggedInUser();

		Notification notification = notificationDao.findById(id).get();
		if (notification.getReceiver().getId() != user.getId())
			throw new IncorrectUserException("This notification doesn't  belong to " + user.getUsername());
		
		user.getNotifications().remove(notification);
		notificationDao.delete(notification);	
		
	}

}

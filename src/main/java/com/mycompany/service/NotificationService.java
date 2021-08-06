package com.mycompany.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.dao.INotificationDAO;
import com.mycompany.entity.Notification;
import com.mycompany.entity.User;

@Service
public class NotificationService {
	
	@Autowired
	private INotificationDAO notificationDao;

	public void save(Notification notification) {
		notificationDao.save(notification);
	}

	public void saveNotification(User loggedInUser, String objectType, String objectURL, Set<User> mentionedUsers) {
		String activityType = "@" + loggedInUser.getUsername() + " mentioned you in a " + objectType;
		for (User user : mentionedUsers) {
			Notification notification = new Notification(loggedInUser.getId(), activityType, objectType, objectURL);
			notification.setReceiver(user);
			save(notification);
		}
	}

}

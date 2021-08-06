package com.mycompany.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.INotificationDAO;
import com.mycompany.entity.Notification;
import com.mycompany.entity.User;

@Service
@Transactional
public class NotificationService {
	
	@Autowired
	private INotificationDAO notificationDao;

	public void save(Notification notification) {
		notificationDao.save(notification);
	}

	public void saveNotification(User sender, String activityType, String objectType,
						String objectURL, Set<User> receivers) {
		for (User user : receivers) {
			Notification notification = new Notification(sender.getId(), activityType, objectType, objectURL);
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
			notification = new Notification(null, activityType, objectType, objectURL);
		notification.setReceiver(receiver);
		save(notification);
	}

}

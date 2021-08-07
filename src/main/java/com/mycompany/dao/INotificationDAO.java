package com.mycompany.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.Notification;
import com.mycompany.entity.User;

public interface INotificationDAO extends CrudRepository<Notification, Integer> {

	public List<Notification> getAllNotificationsByReceiver(User user);

}

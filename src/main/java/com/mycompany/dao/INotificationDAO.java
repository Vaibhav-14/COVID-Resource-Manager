package com.mycompany.dao;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.Notification;

public interface INotificationDAO extends CrudRepository<Notification, Integer> {

}
